---
layout: page
title: Ramapriyan Srivatsan Purisai Devarajan's Project Portfolio Page
---

### Project: Around the World in $80

Around the World in $80 is a desktop application that allows group travellers to split bills between different contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project. [RepoSense link]()

### Features
* **New Feature**: Constructed the `deletegroup` feature
    * What it does: Allows the user to delete a group with a command.
    * Justification: This feature is crucial as the user should be able to delete groups once they are obsolete or if the user has made an error.
    * Highlights: This implementation added in a new command `deletegroup` to delete a group by name.
    * Contribution: Built the entire framework for the feature.

* **New Feature**: Contributed to `creategroup` feature.
  * What it does: Allows the user to create a group with select members with a command.
  * Justification: This feature is crucial as the user should be able to create groups with select members based on their travels.
  * Highlights: This implementation added in a new command `creategroup` to create a group with select members.
  * Contribution: Adapted command format and parser. Contributed significantly to the logic for storage of groups.

### Others
* **Project management**
    * Managed releases: -

* **Documentation**:
    * User Guide:
        * Added documentation for the feature `delete group`.
        * Added details of `Quick Start`.
    * Developer Guide:
        * Added use cases for `delete group` and `help`.
    * Created labels for issue-tracking and documentation.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#113]()
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
