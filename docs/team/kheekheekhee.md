---
layout: page
title: Nicholas Ee's Project Portfolio Page
---

### Project: Around the World in $80

Around the World in $80 is a desktop application that splits bills between different contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project. [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=kheekheekhee&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17)

### Features
* **New Feature**: Added the ability to add expenses in groups.
  * What it does: Allows the user to log their expenses within the specified group.
  * Justification: This feature is crucial as the user should be able to log various expenses into respective groups.
  * Highlights: This implementation added in a new command `expense` to add expenses to groups.
  * RepoSense: [link](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/135)
  
* **New Feature**: Added the ability to track expenses for everyone in the group to be calculated later on.
  * What it does: Allows users to easily split their expenses.
  * Justification: This feature is crucial as users should be able to easily split their expenses with the use of a command.
  * Highlights: This implementation adds in hashmaps into expenses and groups to track total expenses.
  * RepoSense: [link](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/8)

* **New Feature**: Added the ability to exclude people from an expense.
  * What it does: Allows users to be excluded from an expense.
  * Justification: This feature is crucial as users should be able to be excluded from an expense if they did not participate in the activity.
  * Highlights: This implementation adds in the ability for the user to exclude individuals from an expense which will result in the expense not being split among excluded members.
  * RepoSense: [link](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/11)

* **New Feature**: Added the ability to include individual payments in an expense.
  * What it does: Allows users to enter special payments for individuals within an expense.
  * Justification: This feature is crucial as users should be able to enter payments made by an individual which are not meant to be split among the other members of the group.
  * Highlights: This implementation adds individual payments to the specified person only and subtracts it from the total cost so it will not be split among other members.
  * RepoSense: [link](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/11)
  
* **Enhancements to Existing Feature**:
  * Changed the app's logo to AWE's logo. [\#255](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/255)
  * Updated the UI to display AWE's logo on the main window. [\#256](https://app.codecov.io/gh/AY2122S1-CS2103T-F13-1/tp/compare/256)
  

### Others
* **Project management**
  * Managed releases: -

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `expenses`
    * Adjusted diagram sizes.
    * Added figure numbers for all diagrams.
  * Developer Guide:
    * Added use cases for `edit a person`, `add expense` and `delete a person`.
    * Added implementation, test cases and use cases for `add expense`.
    * Added non-functional requirements.
    * Updated model diagram.
    * Added design considerations for `add expense`.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#1]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
