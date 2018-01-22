# AlarmClock
Build an effective alarm app using AlarmManager / SyncAdapter / JobScheduler

### AlarmManager

**Pros**
- Provide a way to access alarm service.
- Reduce coupling with lifecycle
- Batch alarm services with similar interval times by using `inexact`

**Cons**
- Alarms are wiped out when user reboots so we need to register `RECEIVE_BOOT_COMPLETE` 
- Allow perrmison WAKE_LOCK. Imagine if there are 20 applications can wake devices at night. Ting .. Ting .. Ting (x20)
- Be careful when using for network call
- Poor design cause battery drain.

**How to implement?**
- Keep alarm frequency and device waking to a minimum
- Randomness to the alarm’s timing when performing network requests so as to not overload a server
- Avoid clock time and precise timing unless the application needs to notify the user of something at a specific time, and consider another scheduler for networking.

### JobSchedulers

**Pros**
- Can run long execution like network call or database query
- Minimize delay and improve deadline
- Optimize for battery and other resources

**Cons**
- Only support devices from Lollipop and above
- Complext to implement

**How to implement?**
- Create a class that extends `SchdulerService, implement `onStartJob()` and `onStopJob`
- Return true in `onStartJob()` if you want to process the service ( = if you want to receive `onStopJob` callback)
- Return true in `onStopJob()` if you want to retry the job. False if want to drop the job.
- Create `JobInfor.Builder#build()` then call `JobSchduler#schedule()`



