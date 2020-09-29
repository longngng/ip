# Duke project 


## Table of Contents

[1. Introduction](#1-introduction) <br>
[2. Quick start](#2-quick-start) <br>
[3. Features](#3-features) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.1 Viewing help](#31-viewing-help-help) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.2 Adding a todo](#32-adding-a-todo-todo) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.3 Adding an event](#33-adding-an-event-event) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.4 Adding a deadline](#34-adding-a-deadline-deadline) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.5 Listing tasks](#35-listing-all-the-tasks-list) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.7 Finding tasks](#37-finding-a-task-find) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.8 Deleting a task](#38-deleting-a-task-delete) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.9 Exiting](#39-existing-the-program-bye) <br>
[4. Command summary](#4-command-summary)
## 1. Introduction
This is my individual project for CS2113T - Software engineering and OOP at NUS. It is a command line application to help user manage everyday tasks. Given below are instructions on how to use it.

## 2. Quick Start
Prerequisites: JRE 11
1. Download the latest jar file from Github.
1. Open a shell, for example  *cmd, git bash*
1. Change the directory to the location of the jar file
1. Run the program by `java -jar ip.jar`
1. If the setup is correct, you should see the following greetings:
```
   Hello I'm Duke
   What can I do for you?
```

## 3. Features
**Notes about the command format**

Words in <UPPER_CASE> are the parameters to be supplied by the user
 
### 3.1. Viewing help: `help`
Lists out the available commands with explanations.
 
Format: `help`

### 3.2. Adding a todo: `todo`
Adds a todo task to the list of tasks and updates the records.
The new task added is assumed to be not done.

Format: `todo <TODO_DESCRIPTION>`

>Example
```
>>> todo read book
Got it! I've added this task:
  [T][X] read book
Now, you have 1 task in the list.
```

### 3.3. Adding an event `event`
Adds an event with happening time to the list of tasks and updates the records. 
The new task added is assumed to be not done.

Format: `event <EVENT_DESCRIPTION> /at <EVENT_DATE>`
* The <EVENT_DATE> must be in the format yyyy-mm-dd

>Example
```
>>>event oral presentation 1 /at 2020-09-01
Got it. I've added this task:
  [E][✘] oral presentation 1 (at: Sep 1 2020)
Now you have 2 tasks in the list.
```

### 3.4. Adding a deadline `deadline`
Adds a task with deadline to the list of tasks and updates the records. 
The new task added is assumed to be not done.

Format: `deadline <DEADLINE_DESCRIPTION> /by <DEADLINE_DATE>`
* The <DEADLINE_DATE> must be in the format yyyy-mm-dd.

>Example
```
>>>deadline return book /by 2020-09-12
Got it. I've added this task:
  [D][✘] return book (by: Sep 12 2020)
Now you have 3 tasks in the list.
```

### 3.5. Listing all the tasks `list`
Lists out all the existing tasks.

Format: `list`

>Example
```
>>>list
1.[T][✘] read book
2.[E][✘] oral presentation 1 (at: Sep 1 2020)
3.[D][✘] return book (by: Sep 12 2020)
```

### 3.6. Mark a task as done `done`
Marks a task as done and updates the record.

Format: `done <INDEX>`
* The index of the task can be read from using `list` command.
* The index must be a **positive integer** 1, 2, 3, ...

>Example
```
>>>done 1
Nice! I've marked this task as done:
  [T][✓] read book
```

### 3.7. Finding a task `find`
Lists out all the tasks that contain a keyword

Format: `find <KEYWORD>`
>Example
```
>>>find book
Here are the matching tasks in your list:
1.[T][✓] read book
2.[D][✘] return book (by: Sep 12 2020)
```

### 3.8. Deleting a task `delete`
Deletes a task from the list and updates the record.

Format: `delete <INDEX>`
* The index of the task can be read from using `list` command.
* The index must be a **positive integer** 1, 2, 3, ...

>Example
```
>>>delete 2
Noted! I've removed this task:
  [E][✘] oral presentation 1 (at: Sep 1 2020)
Now you have 2 tasks in the list.
```

### 3.9. Exiting the program: `bye`
Terminates the program and saves the recorded information to *~/data/duke.txt*

Format: `bye`

## 4. Command summary

Command |Syntax| Command description and example 
------------| ------ | ------------- 
help |`help`|prints out the list of available commands and their descriptions
todo |`todo <DESCRIPTION>` |adds a new todo task
event |`event <DESCRIPTION> /at <DATE>` |adds a new event task
deadline |`deadline <DESCRIPTION> /by <DATE>`|adds a new deadline task
list |`list`|lists out the existing tasks
done |`done <INDEX>` |marks a task as done
find |`find <KEYWORD>`|lists out the tasks that contain a keyword
delete |`delete <INDEX>`|deletes a task in the list
bye |`bye`|terminates the program



