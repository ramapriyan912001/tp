package seedu.awe.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.awe.logic.commands.exceptions.CommandException;
import seedu.awe.model.Model;
import seedu.awe.model.group.Group;
import seedu.awe.model.group.GroupName;
import seedu.awe.model.tag.Tag;

public class GroupRemoveTagCommand extends Command {
    public static final String COMMAND_WORD = "groupremovetag";
    public static final String MESSAGE_SUCCESS = "Tag(s) removed from group";
    public static final String MESSAGE_ERROR = "Tag(s) not removed from group. Make sure to use exact tag names.";
    public static final String MESSAGE_NONEXISTENT_TAG = "The tag \"%1$s\" is not found in the group.";
    public static final String MESSAGE_USAGE = "groupaddtag gn/[GROUPNAME] n/[TAG1] n/[OPTIONAL TAG2]";
    public static final String MESSAGE_NONEXISTENT_GROUP = "Group %1$s does not exist.";

    private final GroupName groupName;
    private final Set<Tag> tagsToBeRemoved;
    private final boolean isValidCommand;

    /**
     * Creates a GroupRemoveTagCommand to remove the specified {@code Tag}
     */
    public GroupRemoveTagCommand(GroupName groupName, Set<Tag> tagsToBeRemoved, boolean isValidCommand) {
        requireAllNonNull(groupName, tagsToBeRemoved, isValidCommand);
        this.groupName = groupName;
        this.tagsToBeRemoved = tagsToBeRemoved;
        this.isValidCommand = isValidCommand;
    }

    public GroupName getGroupName() {
        return groupName;
    }

    public Set<Tag> getTagsToBeRemoved() {
        return tagsToBeRemoved;
    }

    public boolean getValidCommand() {
        return isValidCommand;
    }

    /**
     * Returns int object tracking the number of non-matching tags between this.tagsToBeRemoved and otherTags.
     *
     * @param numberOfNonMatchingTags int value to track the number of tags in otherTags
     *                                   that are not present in this.tagsToBeRemoved.
     * @param tag Tag object that is being searched for in otherTags.
     * @param otherTags List of Tag objects from another instance of GroupRemoveTagCommand.
     * @return int object to track the number of tags in otherTags that are not present in this.tagsToBeRemoved.
     */
    public int checkForTag(int numberOfNonMatchingTags, Tag tag, Set<Tag> otherTags) {
        for (Tag otherTag : otherTags) {
            if (tag.equals(otherTag)) {
                numberOfNonMatchingTags--;
                break;
            }
        }
        return numberOfNonMatchingTags;
    }

    /**
     * Returns a boolean object representing if this.tagsToBeRemoved contains the same Tag objects as otherTags.
     *
     * @param otherTags List of Tag objects from another instance of GroupRemoveTagCommand.
     * @return boolean object representing if this.tagsToBeRemoved contains the same Tag objects as otherTags.
     */
    public boolean checkSameTags(Set<Tag> otherTags) {
        int numberOfNonMatchingTags = otherTags.size();
        for (Tag tag : this.tagsToBeRemoved) {
            checkForTag(numberOfNonMatchingTags, tag, otherTags);
        }
        return numberOfNonMatchingTags == 0;
    }

    /**
     * Returns Set of Tag objects representing the remaining tags after removing a given set of tags.
     *
     * @param tagsFromOldGroup Set of Tag objects representing original tags in a group.
     * @param tagsToBeRemoved Set of Tag objects representing tags to be removed from a group.
     * @return Set of Tag objects representing the remaining tags after removing a given set of tags
     * @throws CommandException If tag to be removed is not present in the set of original tags.
     */
    public Set<Tag> removeTags(Set<Tag> tagsFromOldGroup, Set<Tag> tagsToBeRemoved) throws CommandException {
        Set<Tag> remainingTags = new HashSet<>(tagsFromOldGroup);
        for (Tag tagToBeRemoved : tagsToBeRemoved) {
            boolean tagToBeRemovedIsPresent = remainingTags.remove(tagToBeRemoved);
            if (!tagToBeRemovedIsPresent) {
                throw new CommandException(String.format(MESSAGE_NONEXISTENT_TAG, tagToBeRemoved.getTagName()));
            }
        }
        return remainingTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!isValidCommand) {
            throw new CommandException(MESSAGE_ERROR);
        }

        Group oldGroup = model.getGroupByName(groupName);
        if (Objects.isNull(oldGroup)) {
            throw new CommandException(String.format(MESSAGE_NONEXISTENT_GROUP, groupName));
        }
        Set<Tag> tagsFromOldGroup = oldGroup.getTags();
        Set<Tag> remainingTags = removeTags(tagsFromOldGroup, tagsToBeRemoved);
        Group newGroup = new Group(groupName, oldGroup.getMembers(), remainingTags);
        model.setGroup(oldGroup, newGroup);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GroupRemoveTagCommand)) {
            return false;
        }
        GroupRemoveTagCommand otherCommand = (GroupRemoveTagCommand) other;
        if (this.isValidCommand == otherCommand.getValidCommand()
                && checkSameTags(otherCommand.getTagsToBeRemoved())
                && this.groupName.equals(otherCommand.getGroupName())) {
            return true;
        }
        return false;
    }
}

