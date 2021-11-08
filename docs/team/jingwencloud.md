---
layout: page
title: Ong Jingwen's Project Portfolio Page
---

### Project: Around the World in $80

Around the World in $80 is a desktop application that splits bills between different contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project. They can be viewed [here](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Jingwencloud&tabRepo=AY2122S1-CS2103T-F13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs&authorshipIsBinaryFileTypeChecked=false&zFR=false).

* **New Feature**: Added the ability to list expenses of a specified group.
    * What it does: Allows the user to view all the expenses for a certain group, where the description, cost and payer
    * of the expenses will be shown on display.
    * Justification: This feature is crucial as the user should be able to view their expenses for different groups.
    * Highlights: This implementation added a new command 'expenses' to list expenses of a group.
    * Contribution: Built the entire framework for this feature.

* **New Feature**: Added the ability to find expenses for a specified group.
    * What it does: Allows the user to find all the expenses containing the input keyword(s) for a certain group.
    * Justification: This feature is crucial as the user should be able to view the specific expenses they wish
    * to view more conveniently. Users would be able to search for expenses within groups, so that they do not need
    * to manually filter the matching expenses by their groups.  
    * Highlights: This implementation added a new command 'findexpenses' to find expenses of a group.
    * Contribution: Built the entire framework for this feature.

* **Enhancements to existing features**:
    * Added the UI to display the results of the `transactionsummary` command.
    * Making the help window pop-up when there are errors in the Json File detected while opening the application, so 
      that users can refer to the user guide for help.
    * Making the list of expenses on display automatically update when a new expense is added. 

### Others
* **Project management**
    * Release management:[v1.4](https://github.com/AY2122S1-CS2103T-F13-1/tp/releases/tag/v1.4)
  **Testing**
    * Wrote tests for the following:
        * `Group` model related classes
        * `Storage` model related classes
        * `ListExpensesCommand`
        * `ListExpensesCommandParser`
        * `FindExpensesCommand`
* **Documentation**:
    * User Guide:
        * Added documentation for `find expenses` feature
        * Added documentation for `view expenses` feature
    * Developer Guide:
        * Added implementation details and design considerations for `find expenses`
        * Added user stories 
        * Added use cases for the `list expenses` and `find expenses` feature.
        * Updated diagrams and added activity diagram and sequence diagrams for `find expenses`
        * Added test cases for manual testing for `find expenses` and `expenses` command
* **Community**:
    * Reviewed and merged PRs for team members.
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/Jingwencloud/ped/issues/1), [2](https://github.com/Jingwencloud/ped/issues/3), [3](https://github.com/Jingwencloud/ped/issues/4))
