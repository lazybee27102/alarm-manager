package manager.android.simple.example;

public class Sample {
    public enum SampleName {
        ALARM_MANAGER,
        JOB_SCHEDULER,
        SERVICE_INTENT_SERVICE,
        VIBRATION_BROADCAST_RECEIVER,
        LOCAL_BROADCAST_RECEIVER
    }

    private final String title;
    private final String content;
    private final SampleName sampleName;

    public Sample(String title, String content, SampleName sampleName) {
        this.title = title;
        this.content = content;
        this.sampleName = sampleName;
    }

    public SampleName getSampleName() {
        return sampleName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
