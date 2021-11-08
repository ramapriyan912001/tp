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

### Bug Fixes
* **severity.HIGH** Ensure that the input for expenses cannot be negative. [\#150](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/150)
  * What happened: Negative inputs were able to inputted when using `addexpense`.
  * Pull request: [\#179](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/179)

* **severity.HIGH** Entering a large number for `addexpense` results in the app crashing [\#324](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/324)
  * What happened: The value of a large expense was being converted into scientific notation and thus could not saved properly.
  * Pull request: [\#359](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/359)

* **severity.MED** Error message for `addexpense` is wrong [\#310](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/310)
  * What happened: Entering a group that does not exist shows an error message that the payer is not part of the group rather than indicating that the group does not exist.
  * Pull request: [\#359](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/359)
  
* **severity.MED** An expense of zero cost was able to be added through rounding of a number close to zero. [\#393](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/393)
  * What happened: The cost of the expense was rounded off to zero due to it being too close to zero.
  * Pull request: [\#413](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/413)

* **severity.LOW** Transaction summary lacked a dollar sign when displayed. [\#329](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/329)
  * What happened: Displayed transaction summary lacked a dollar sign which made its purpose confusion.
  * Pull request: [\#359](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/359)

### Testing
* Wrote all test cases for the following classes
  * `AddExpenseCommand`
  * `AddExpenseCommandParser`
  * `Cost`
  * `Description`
  * `Expense`
  * `ExpenseList`
  * `IndividualAmount`
  * `FindExpenseCommandParser`

### Others
* **Project management**
  * Managed releases: - [v1.4b](https://github.com/AY2122S1-CS2103T-F13-1/tp/releases/tag/v1.4b)
  * Participated and added to project planning every week - [1](https://github.com/AY2122S1-CS2103T-F13-1/tp/projects/1)

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
  * Reviewed PRs, provided feedback on code and assisted all other teammates in solving bugs via text.
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/kheekheekhee/ped/issues/1), [2](https://github.com/kheekheekhee/ped/issues/2), [3](https://github.com/kheekheekhee/ped/issues/3))
