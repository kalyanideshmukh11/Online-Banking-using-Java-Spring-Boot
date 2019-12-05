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


### Authors 
Vanditt Sama (014210521)  
Namrata Deshmukh  
Mukesh Mogal  
Kalyani Deshmukh  
