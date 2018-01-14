# AlarmClock
Build an effective alarm app using AlarmManager / SyncAdapter / JobScheduler

### AlarmManager

**Pros**
- Provide a way to access system alarm service
- Reduce coupling with lifecycle
- Can batch alarm services with similar interval of multiple application by using `inexact`

**Cons**
- Alarms are wiped out when user reboots -> Need to register `RECEIVE_BOOT_COMPLETE` 
- Allow perrmison WAKE_LOCK ???
- Be careful when using for network call
- Poor design cause battery drain.

**How to implement?**
- Keep alarm frequency and device waking to a minimum
- Randomness to the alarm’s timing when performing network requests so as to not overload a server
- Avoid clock time and precise timing unless the application needs to notify the user of something at a specific time, and consider another scheduler for networking.

