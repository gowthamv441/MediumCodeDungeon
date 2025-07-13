class Solution {

    //[Learning:: enum string with value as int (for easier access -  see sort function)]
    enum EventType {
        START(0),
        END(1);

        private final int value;

        EventType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    class MeetingEvent {

        int time;
        EventType eventType;

        MeetingEvent(int time, EventType eventType) {
            this.time = time;
            this.eventType = eventType;
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        List<MeetingEvent> meetingEventList = new ArrayList<MeetingEvent>();

        //Loop through intervals and create a meeting event;
        for (int[] interval : intervals) {
            meetingEventList.add(
                new MeetingEvent(interval[0], EventType.START)
            );
            meetingEventList.add(new MeetingEvent(interval[1], EventType.END));
        }

        //[Learning :: List Sort using if/ else case]
        // sort the events based on time
        meetingEventList.sort((a, b) -> {
            if (a.time == b.time) {
                //[Learning: Logic :: if both share same time then clear the room first if there is any departure event then use same meeting room]
                return Integer.compare(
                    b.eventType.getValue(),
                    a.eventType.getValue()
                );
            } else {
                return Integer.compare(a.time, b.time);
            }
        });

        //Loop through events if start increase the roomcount else decrease room count
        int roomCount = 0;
        int maxRoomCount = 0;
        for (MeetingEvent meetingEvent : meetingEventList) {
            if (meetingEvent.eventType == EventType.START) roomCount++;
            else roomCount--;
            //record max roomCount seen
            maxRoomCount = Math.max(roomCount, maxRoomCount);
        }

        //return max room count
        return maxRoomCount;
    }

    // TC : O(NLogN)
    // SC : O(2N) => O(N)
}
