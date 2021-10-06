---
layout: page
title: Chrus Chong's Project Portfolio Page
---

### Project: Around the World in $80

Around the World in $80 (AWE) is a desktop application that splits bills between different contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project. [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=chrus&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=false&zFR=false)

### Features
* **New Feature**: Constructed the `creategroup` feature for AWE.
    * What it does: Allows user to create a new travel group with the command line interface.
    * Justification: This feature is crucial as the user should be able to create groups to record travel expenses.
    * Highlights: This implementation added in a new command `creategroup` to create a new group. It creates a new travel group with the specified members and 
    * Contribution: Built the entire framework for this feature.

* **New Feature**: Developed storage functionalities for `creategroup` and `deletegroup` feature.
  * What it does: Allows group data to be saved upon creation or deletion of group.
  * Justification: This feature is crucial as the changes to travel groups should be automatically saved.
  * Highlights: This implementation ensures that the most up-to-date group data is saved into `/data/addressbook.json` with each command.
  * Contribution: Pieced together the basic framework provided by [@ramapriyan912001](https://github.com/ramapriyan912001) to develop fully functional storage abilities for `creategroup` and `deletegroup`.
  
### Others
* **Project management**
    * Managed releases: -

* **Documentation**:
    * User Guide:
        * Added documentation for the features `create group`
    * Developer Guide:
        * Created table of contents for ease of navigation.
        * Rearranged all user stories to ensure they follow decreasing order of priority.
        * Added use cases for `Add a Person`, `Clear All Entries` and `Create Travel Group`.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#124](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/124)
    * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
    * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
    * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
