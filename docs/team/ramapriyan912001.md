---
layout: page
title: Ramapriyan Srivatsan Purisai Devarajan's Project Portfolio Page
---

### Project: Around the World in $80

Around the World in $80 is a desktop application that allows group travellers to split bills between different contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 22 kLoC.

My contributions to the project can be viewed [here](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=ramapriyan&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=false&zFR=false).

### Features
* **New Feature**: Contributed to delete group feature
    * What it does: Allows the user to delete a group with a command.
    * Justification: This feature is crucial as the user should be able to delete groups once they are obsolete or if the user has made an error.
    * Highlights: This implementation added in a new command `deletegroup` to delete a group by name.
    * Contribution: Built the entire framework for the feature including command and parser classes.

* **New Feature**: Contributed to create group feature.
  * What it does: Allows the user to create a group with select members with a command.
  * Justification: This feature is crucial as the user should be able to create groups with select members based on their travels.
  * Highlights: This implementation added in a new command `creategroup` to create a group with select members.
  * Contribution: Adapted command format and parser. Contributed significantly to the logic for storage of groups.

* **New Feature**: Contributed to delete expense feature.
    * What it does: Allows the user to delete an expense with a command.
    * Justification: This feature is crucial as the user should be able to delete errant expense entries.
    * Highlights: This implementation added in a new command `deleteexpense` to delete an expense by index position on list shown on screen.
    * Contribution: Built the entire framework for the feature including command and parser classes.

* **New Feature**: Contributed to calculate payments feature.
    * What it does: Provides the users with a set of easy transactions for them to settle their debts from the trip.
    * Justification: This feature is crucial as the users should be able to efficiently settle their debts in a manner that leaves no room for disputes and no room for error by manual computation.
    * Highlights: This implementation added in a new command `calculatepayments` to provide users with a number of suggested transactions to settle their debts from the trip.
    * Contribution: Built the entire framework for the feature including command and parser classes.
  
* **Miscellaneous**:
    * Introduced constraints to `editcontact` and `deletecontact` commands wherein user must be viewing a contacts page if they wish to execute these commands.
    * Introduced constraints to the `deleteexpense` command wherein user must be viewing an expenses page if they wish to execute this command.
    * Implemented classes for UI display of payments upon execution of `calculatepayments` command.


### Testing
* Wrote automated tests for the following classes
  * `DeleteGroupCommand`
  * `DeleteGroupCommandParser`
  * `CalculatePaymentsCommand`
  * `CalculatePaymentsCommandParser`
  * `Payment`
  * `PaymentList`
  * `Awe`
  * `ModelManager`
  * `UserPrefs`
  
### Bug Fixes
* **severity.HIGH** Does not take note of the difference in sign of surplus between those who are owed and those who owe. [\#274](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/276)
  * Bug description: The `calculatepayments` command does not take into account people who owe money, and only takes into account those who are owed money.
  * Pull request: [\#158](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/274)

* **severity.HIGH** Storage of IndividualAmount class failed [\#224](https://github.com/AY2122S1-CS2103T-F13-1/tp/issues/213)
  * Bug description: The `JsonAdaptedIndividualAmount` took in a Person parameter rather than a JsonAdaptedPerson parameter.
  * Pull request: [\#207](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/205)

### Others
* **Project management**
  * Managed releases: [v1.3](https://github.com/AY2122S1-CS2103T-F13-1/tp/releases/tag/v1.3)
    
* **Miscellaneous**
    * Created storage classes for expenses and groups.
    * Created UI classes for displaying of payments upon the execution of the `calculatepayments` command.

* **Documentation**:
    * User Guide:
        * Documented delete group, create group, delete expense, and calculate payments features.
        * Added details of `Quick Start`.
        * Added details of `deletegroup`, `creategroup`, `deleteexpense`, `calculatepayments` and `help` commands to Command Summary table.
        * Added significant details of the `About The Document` section.
    * Developer Guide:
        * Added implementation details for add contact, delete group, create group, delete expense, and calculate payments features, including sequence and activity diagrams.
        * Added use cases for editing a contact, deleting a contact, deleting a travel group, deleting an expense, calculating payments, and help.
        * Added manual test cases for editing a contact, deleting a contact, deleting a travel group, deleting an expense, and calculating payments.
    * Created labels for issue-tracking and documentation.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#113](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/113), [\#121](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/121), [\#166](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/166), [\#380](https://github.com/AY2122S1-CS2103T-F13-1/tp/pull/380)
    * Collaborated with [@chrus-chong](https://github.com/chrus-chong) on `creategroup` features.
    * Contributed to CS2103T community by initiating discussion: [\#282](https://github.com/nus-cs2103-AY2122S1/forum/issues/282)
    * Reported bugs and suggestions for other teams in the class: [\#W11-1](https://github.com/ramapriyan912001/ped/issues)
