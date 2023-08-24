# Test Plan

**Author**: Ye Li, ylu376

## 1 Testing Strategy

### 1.1 Overall strategy

The application is divided into four sections, with the combination of interface and job functionality for each section. Hence, the unit test includes the interfaces test, interacting between main page and section page; and job function tests. The integration test includes any possible combination test between sections, including adding new job offer, list of job offers should be changed accordingly; changing comparison settings, list of job offers should be changed based on new ranking. The system test is to make sure all interface and job functions work well, and the previous job offer should persist between runs. 

Each section's interface and functionality is assigned to one team member, when doing the unit test, the person who implemented one section should test on their own sections. Currently, we plan to do integration and system test for all of the team members.

### 1.2 Test Selection

The test case is mainly designed by Black-Box testing, as the application itself is well designed as a tool. We will follow the Category Partition Method.

### 1.3 Adequacy Criterion

We will use Category Partition Method for most cases. In the system test level, making sure everything is running smoothly without any error on performance downgrade during application running.

### 1.4 Bug Tracking

We will use Github's "Creating an issue" for bug tracking. 

### 1.5 Technology

We will focus on JUnit for the test on job functionality. For more complicated test, integration or system test, we will do manually  

## 2 Test Cases

| Test Level  | Purpose                                                    | Step                                                                      | Expected Result                                    | Actual Results | Pass/Fail | Others |
|-------------|------------------------------------------------------------|---------------------------------------------------------------------------|----------------------------------------------------|----------------|-----------|--------|
| Unit        | Test current job save                                 | switch between main and current job, enter current job info, save                                       | switch correctly and save correctly when next time switch to the current job tab                                  |       switch and save correctly         |     Pass      |        |
| Unit        | Test entering job cancel                             | switch between main and current job, modified the title of previously entered, go back                                      | previously entered job should show when switch enter job interface, no change made when go back without saving      | previously entered job info shows, modified unsuccesfully         |      Pass     |        |
| Unit        | Test entering job offer, and compare with current job                                      | after entering, save, and compare with current job                                         | job offer saved, current job and current job offer show in a new tab                                  |        job offer saved, current job and job offer show in a new tab        |      Pass     |        |
| Unit        | Test entering job offer, and add another job                          | add another job, save, and compare with current job                                       | another job offer saved , current job and current job offer show in a new tab     |        job offer saved, current job and job offer show in a new tab        |       Pass    |        |
| Unit        | Test entering weight setting                               | enter new weight setting, and save                                      | weight setting saved, load correctly next time editing it    |       weight setting saved, load correctly next time editing it        |      Pass     |        |
| Unit        | Test ranking function based on the weight                  | switch between main and ranking                                           | ranking should be generated based on the weight    |        ranking should be generated based on the weight        |    Pass       |        |
| Unit        | Test comparing function                                    | pick two job offer                                                        | a table comparing two jobs should display          |         a table comparing two jobs should display       |      Pass     |        |
| Integration | Test the interaction between job offer and ranking/compare | Adding new job offer and check ranking/compare                            | ranking/compare should be updated correctly        |         ranking/compare should be updated correctly       |     Pass      |        |
| Integration | Test the interaction between weight and ranking/compare    | update weight and check ranking/compare                                   | ranking/compare should be updated correctly        |        ranking/compare should be updated correctly        |     Pass      |        |
| System      | Test the application all interface and functions           | Test all job functions, add job details, offers, weights, ranking/compare | all interface/function works, no performance issue |         all interface/function works, no performance issue       |      Pass     |        |