# Team Project: Online banking system

A banking application which exposes API calls to add new accounts, perform transactions and view transaction history.

## Design Decisions  
### Springboot  
Using the spring framework for our application allowed us to set up Java application with easily testable and reusable code.
The configuration required to get the application running was minimal as a result.  
The added support for data access frameworks like hibernate and JDBC allowed us to easily integrate database transactions into our application.

### MySQL Database hosted on Amazon S3
For an application heaviliy dependant on integrity of data like the online banking system, a relational database was found to be a more reliable option to maintain data consistency and ease of monitoring data.
The database instance was hosted on Amazon's S3 cloud storage during development. This approach provided two advantages:  
1. All team members could perform development testing in sync.
2. No need to re-deploy database instance

### Amazon AWS Auto Scaled EC2 Cluster
Auto scaling automatically monitors and adjusts resources allocation to provide reliable perfonmance of applications hosted on AWS under variable workloads.
This step will let us ensure that we have the optimum number of EC2 instances available to handle the load of the banking system.  
<img src="https://user-images.githubusercontent.com/38210397/70234463-90d74300-1715-11ea-860c-5d8ee455c4a8.PNG" width="300" height="250">  

## Feature Set
* Add a new account to the system.
* Remove existing accounts (only if there is no balance in the account)
* Internal transaction, from one account to another.
* External transactions to non-member accounts of the bank.
* Setting up recurring transactions for internal or external payments
* Search and view transactions by date/account number etc.
* Manual transactions generated by admin.
* Appropriate validations during a transaction are included.
## Weekly Scrum Report
> ### Week 1 (11/02/2019 – 11/08/2019)
#### Kalyani Deshmukh
##### What tasks did I work on / complete?
Requirement Gathering for the adding bank account and closing bank account.
Understanding the technical requirements for building this application.
##### What am I planning to work on next?
Setting up the environment and software installation.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Namrata Deshmukh
##### What tasks did I work on / complete?
Requirement Gathering for the View and search Transactions - for credits/debits/checks/fees - up to last 18 months functionality
Understanding the technical requirements for building this application.
##### What am I planning to work on next?
Setting up the environment and software installation.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Mukesh Mogal
##### What tasks did I work on / complete?
Requirement Gathering for the Set up recurring or one-time Bill payment for external payees functionality.
Understanding the technical requirements for building this application.
##### What am I planning to work on next?
Setting up the environment and software installation.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Vanditt Sama
##### What tasks did I work on / complete?
Requirement Gathering for the Transfer between accounts - one time or recurring functionality.
Understanding the technical requirements for building this application.
##### What am I planning to work on next?
Setting up the environment and software installation.
##### What tasks are blocked waiting on another team member?
No blocker.

> ### Week 2 (11/09/2019 – 11/15/2019)
#### Kalyani Deshmukh
##### What tasks did I work on / complete?
Finalized the table structure required for account table for adding and closing bank account. 
Created the accounts table in database. 
Started coding add account API.
##### What am I planning to work on next?
Complete code for adding account.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Namrata Deshmukh
##### What tasks did I work on / complete?
Finalized the table structure required for transaction table.
Created the transactions table in database. 
Started coding Transactions - for credits/debits - up to last 18 months.
##### What am I planning to work on next?
Complete code for Transactions - for credits/debits - up to last 18 months.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Mukesh Mogal
##### What tasks did I work on / complete?
Started coding for Set up recurring Bill payment for external payees functionality.
##### What am I planning to work on next?
Complete code for Set up recurring Bill payment for external payees functionality.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Vanditt Sama
##### What tasks did I work on / complete?
Started coding for Transfer between accounts - one time
##### What am I planning to work on next?
Complete code for Transfer between accounts - one time.
##### What tasks are blocked waiting on another team member?
No blocker.

> ### Week 3 (11/16/2019 – 11/22/2019)
#### Kalyani Deshmukh
##### What tasks did I work on / complete?
Completed code for adding account.
Write code for closing bank account.
##### What am I planning to work on next?
Complete code for closing bank account
##### What tasks are blocked waiting on another team member?
No blocker.
#### Namrata Deshmukh
##### What tasks did I work on / complete?
Completed code for Transactions - for credits/debits - up to last 18 months.
Write code for Transactions - for checks/fees - up to last 18 months functionality
##### What am I planning to work on next?
Complete code for Transactions - for checks/fees - up to last 18 months functionality
##### What tasks are blocked waiting on another team member?
No blocker.
#### Mukesh Mogal
##### What tasks did I work on / complete?
Completed code for Set up recurring Bill payment for external payees functionality.
Started coding for  Set up one-time Bill payment for external payees functionality.
##### What am I planning to work on next?
Complete code for Set up one-time Bill payment for external payees functionality.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Vanditt Sama
##### What tasks did I work on / complete?
Completed code for Transfer between accounts - one time
Started coding for Transfer between accounts -recurring functionality.
##### What am I planning to work on next?
Complete code for Transfer between accounts -recurring functionality.
##### What tasks are blocked waiting on another team member?
 No blocker.

> ### Week 4 (11/23/2019 – 11/29/2019)
#### Kalyani Deshmukh
##### What tasks did I work on / complete?
Completed coding for adding and closing bank account.
Unit testing for adding bank account and closing bank account.
Integration testing for the application.
##### What am I planning to work on next?
Test application post deployment.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Namrata Deshmukh
##### What tasks did I work on / complete?
Completed coding for the View and search Transactions - for credits/debits/checks/fees - up to last 18 months functionality.
Unit testing of View and search Transactions - for credits/debits/checks/fees - up to last 18 months functionality.
System Testing of the application.
##### What am I planning to work on next?
Test application post deployment.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Mukesh Mogal
##### What tasks did I work on / complete?
Completed coding for the Set up recurring or one-time Bill payment for external payees functionality.
Create docket image of the application in Amazon Containers.
##### What am I planning to work on next?
Test application post deployment.
##### What tasks are blocked waiting on another team member?
No blocker.
#### Vanditt Sama
##### What tasks did I work on / complete?
Completed coding for the Transfer between accounts - one time or recurring functionality.
Deploy APIs to AWS in an Auto Scaled EC2 Cluster with Load Balancer.
##### What am I planning to work on next?
Test application post deployment.
##### What tasks are blocked waiting on another team member?
No blocker.


## XP Core Value
### Simplicity – Namrata Deshmukh
I believed in ‘it is better to do a simple thing today and pay a little more tomorrow to change it’ than ‘to do a more complicated thing today that may never be used anyway’. Took small simple steps to our goal and mitigated failures as they happen. Created something that we are proud of and will maintain it for a long term for reasonable costs.
Communication and Simplicity support each other. The more you communicate the clearer you can see exactly what needs to be done, and you gain more confidence about what really need not be done.
### Communication- Mukesh Mogal
Communication plays a major role in the success of a project. Problems with projects often arise due to lack of communication. Many circumstances may lead to the breakdown in communication. I emphasized continuous and constant communication among the team members. Face-to-Face communication was preferred and is achieved with pair programming and team meetings.
### Feedback – Kalyani Deshmukh
As I was working on the initial API’s of the application I implemented Feedback value. Every commitment was taken seriously by delivering a working software. The software is delivered early to the other team members and a feedback is taken so that necessary changes can be made if needed. Concrete feedback about the current state of the system is priceless. The value of the feedback was a continuously running system that delivered information about itself in a reliable way.
### Respect and Simplicity - Vanditt Sama
Combined with communication, simplicity, and concrete feedback, courage becomes extremely valuable. Respect is a deep value, one that lies below the surface of the other four values. I implemented simplicity value while writing the API’s by building what is needed and asked for. And made sure that respect value is also satisfied by continuously checking  
*	Everyone respects each other as a valued team member.
*	Everyone contributes value such as enthusiasm.

## Agile Methodology- 
Agile is an empirical process which enables continuous learning and adapting to a process to unwind complex problems. Uncovering better ways of developing software by using this method will create a lot of value for the developer.
Team worked on the Agile Method which is an approach to project management utilized in software development. 
Sprint duration- 1 week
Status call- everyday zoom call

## Testing Methodologies:
### Non-functional Testing
Four stages of nonfunctional testing: vulnerability, compatibility, usability and performance.
#### Compatibility Testing :
Compatibility testing involves verifying that an application is fully compatible with the hardware and software it will run on.
#### Usability Testing :
Usability testing involves testing that the application is easy to use and intuitive. 
#### Performance Testing :
Performance testing involves verifying that the application performs well in a variety of situations that could affect users, from heavy load to limited battery power. 

### Functional Testing
Functional testing involves testing an app to ensure it operates properly. The goal of this type of QA testing is to verify that the app works as it should.
#### Unit Testing :
The goal of this stage of testing is to test each unit in isolation without looking at its role within the application as a whole.
#### Integration Testing :
Integration testing involves testing multiple units together to see how they perform functions as a whole. Unlike unit testing, which focuses on one unit in isolation, integration testing is all about seeing how units work together to complete functions.
#### System Testing :
 A typical system test involves running the entire system in order to discover previously unnoticed bugs, stability issues and major problems.


### Authors 
Vanditt Sama (014210521)  
Namrata Deshmukh  
Mukesh Mogal  
Kalyani Deshmukh  
