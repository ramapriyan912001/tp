---
layout: page
title: Marcus Peh's Project Portfolio Page
---

### Project: Around the World in $80

Around the World in $80 is a desktop application that splits bills between different contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project. [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=marcuspeh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

### Features
* **New Feature**: Added the ability to change between contacts and groups view.
  * What it does: Allows the user to see groups / contacts with a command.
  * Justification: This feature is crucial as the user should be able to view the groups and contacts created.
  * Highlights: This implementation added in a new command `groups` to see all groups. It changes the command `list` to `contacts`. `contacts` allows the user to see all contacts.
  * RepoSense: [link](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/121)
  
* **New Feature**: Added the ability to find groups.
  * What it does: Allows the user to find for groups with a command.
  * Justification: This feature will allow the user to check if a group has been created and the details of the group.
  * Highlight: This implementation added in a new command `findgroups` to find for group. It will also change the view from `contacts` to `groups`.
  * RepoSense: [link](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/163)

* **New Feature**: Added the ability to see the total spending per person in a trip
  * What it does: Allows the user to find their expenses in a trip.
  * Justification: This feature will allow the user to track their expenses and cut down spending if needed.
  * Highlight: This implementation added in a new command `transactionsummary` to see expenses.
  * RepoSense: [link](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/206)

* **Enhancements to existing features**:
  * Updated the UI interface layout. [\#121](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/121)
  * Added in buttons to toggle between contacts page and group page [\#121](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/121)

### Bug Fixes
* **severity.HIGH** Edit person does not update groups and expenses. [\#158](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/153)
   * What happen: The command `addPerson` does not replace the instance of the old person from groups and expenses.
   * Pull request: [\#158](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/158)
   
* **severity.HIGH** Creating an empty group without person will crash the program [\#224](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/224)
    * What happen: The command `creategroup` throws a `EmptyGroupsException` and is not handled.
    * Pull request: [\#207](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/207)
    
* **severity.HIGH** Running AWE without data.json will crash the program [\#223](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/223)
    * What happen: Running AWE without data.json will cause a `RuntimeException`. 
    * Pull request: [\#186](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/186)

### Others
* **Project management**
  * Managed releases: [v1.2](https://github.com/AY2122S1-CS2103T-F13-1/tp/releases/tag/v1.2), [v1.2b]()

* **Documentation**:
  * User Guide:
    * Added user guide for the features `groups`, `expenses`and `findgroups`.
    * Added FAQ.
  * Developer Guide:
    * Added use cases for `view groups`, `find person` and `view expenses`.
    * Updated UI segment with class diagram and explanation.
    * Added implementation, test cases  and use cases for `findgroups`.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#113](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/113), [\#116](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/116)
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
