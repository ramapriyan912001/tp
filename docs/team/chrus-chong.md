---
layout: page
title: Chrus Chong's Project Portfolio Page
---

### Project: Around the World in $80

Around the World in $80 (AWE) is a desktop application that splits bills between different contacts.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=chrus&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=false&zFR=false)

### Features
* **New Feature**: Constructed the `creategroup` feature for AWE.
    * What it does: Allows user to create a new travel group with the command line interface.
    * Justification: This feature is crucial as the user should be able to create groups to record travel expenses.
    * Contribution: Built the entire framework for this feature.

* **New Feature**: Constructed the `groupeditname` feature for AWE.
    * What it does: Allows user to change the group name of an existing group with the command line interface.
    * Justification: This feature is important as the user should be able to change a travel group's name.
      The prevents the need for deleting a re-creating a group to change group name.
    * Contribution: Built the entire framework for this feature.

* **New Feature**: Constructed the `groupaddcontact`, `groupremovecontact`, `groupaddtag` and `groupremovetag`feature
for AWE.
    * What it does: Allows user to add and remove members/tags from an existing group with the command line interface.
    * Justification: This feature is important as the user should be able to add and remove members/tags from travel
  groups after creating them. The prevents the need for deleting a re-creating a group to change membership or tag
  status.
    * Contribution: Built the entire framework for this feature.

* **New Feature**: Developed storage functionalities for `creategroup` and `deletegroup` feature.
  * What it does: Allows group data to be saved upon creation or deletion of group.
  * Justification: This feature is crucial as the changes to travel groups should be automatically saved.
  * Highlights: This implementation ensures that the most up-to-date group data is saved into `/data/awe.json` with each
  command.
  * Contribution: Pieced together the basic framework provided by
  [@ramapriyan912001](https://github.com/ramapriyan912001) to develop fully functional storage abilities for
  `creategroup` and `deletegroup`.

### Others
* **Project management**
    * Managed releases: v1.2b

* **Documentation**:
    * User Guide:
        * Added documentation for the features `create group`, `group add tag`, `group remove tag`, `group add contact`,
         `group remove contact`, and `group edit name`
        * Added details for `About the Document`, `Command Format`, `FAQ`
        * Added `Final Word` section
    * Developer Guide:
        * Created table of contents for ease of navigation.
        * Rearranged all user stories to ensure they follow decreasing order of priority.
        * Added use cases for `Add a Person`, `Clear All Entries` and `Create Travel Group`.
        * Added details for implementation of `creategroup`, `groupeditname` features, including sequence and
          activity diagrams.
        * Added details for manual testing of `creategroup`, `groupeditname`,`group add tag`, `group remove tag`,
      `groupaddcontact`, `groupremovecontact` commands.
        * Added use cases for `addcontact`, `clearalldata`, `creategroup`, `help`, `groupeditname`,
         `groupaddtag`, `group remove tag`, `group add contact`, `group remove contact` commands.
        * Wrote the code for the sequence diagram of `creategroup`, upon which a bulk of the other sequence diagrams
      were based on. Refer to the sequence diagram in the
      [Diagram Contributions to Developer Guide](#diagram-contributions-to-developer-guide) section.

* **Major bug fixes**:
  * **severity.HIGH** `groupremovecontact` does not delete the group even when removing the last member of the group.
  [\#341](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/341)
      * Bug description: The `groupremovecontact` command removes the final person in the group, leaving AWE with a
    group that has no members.
      * Pull request: [\#360](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/360)

  * **severity.HIGH** `groupeditname` causes AWE to become unresponsive.
    [\#314](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/314)
      * Bug description: The `groupeditname` causes AWE to become unresponsive upon trying to change a specific group
        name to another group name that already exists within AWE.
      * Pull request: [\#360](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/360)


* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#124](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/124),
      [\#334](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/334),
      [\#411](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/411).
    * Worked together with [@ramapriyan912001](https://github.com/ramapriyan912001) on group features.
    * Contributed to forum discussions (examples: [#38](https://github.com/nus-cs2103-AY2122S1/forum/issues/38),
  [#113](https://github.com/nus-cs2103-AY2122S1/forum/issues/113),
  [#264](https://github.com/nus-cs2103-AY2122S1/forum/issues/264),
  [#342](https://github.com/nus-cs2103-AY2122S1/forum/issues/342))
    * Reported bugs and suggestions for other teams in the class (examples:
  [#1](https://github.com/chrus-chong/ped/issues/1),
  [#2](https://github.com/chrus-chong/ped/issues/2),
  [#3](https://github.com/chrus-chong/ped/issues/3),
  [#4](https://github.com/chrus-chong/ped/issues/4),
  [#5](https://github.com/chrus-chong/ped/issues/5),
  [#6](https://github.com/chrus-chong/ped/issues/6),
  [#7](https://github.com/chrus-chong/ped/issues/7),
  [#8](https://github.com/chrus-chong/ped/issues/8),
  [#9](https://github.com/chrus-chong/ped/issues/9),
  [#10](https://github.com/chrus-chong/ped/issues/10),
  [#11](https://github.com/chrus-chong/ped/issues/11),
  [#12](https://github.com/chrus-chong/ped/issues/12))

<div style="page-break-after: always;"></div>

### Diagram Contributions to Developer Guide

<p align="center">
  <img src="images/CreateGroupActivityDiagram.png" alt="Create Group Activity Diagram" width="400" />
  <br>
    Fig 1. Create Group Activity Diagram
</p>

<p align="center">
  <img src="../images/CreateGroupSequenceDiagram.png" alt="Create Group Sequence Diagram" width="750" />
  <br>
    Fig 2. Create Group Sequence Diagram, upon which a bulk of the other sequence diagrams are based on
</p>

<p align="center">
  <img src="../images/CreateGroupRef.png" alt="Create Group Reference Sequence Diagram" width="600" />
  <br>
    Fig 3. Reference diagram for the Create Group Sequence Diagram
</p>

<p align="center">
  <img src="../images/GroupEditNameSequenceDiagram.png" alt="Create Group Sequence Diagram" width="750" />
  <br>
    Fig 5. Group Edit Name Sequence Diagram
</p>
