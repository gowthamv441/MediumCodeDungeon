class Solution {

    class Event {

        int startDay;
        int endDay;

        Event(int startDay, int endDay, int attendedTime) {
            this.startDay = startDay;
            this.endDay = endDay;
        }
    }

    private List<Event> prepareEventList(int[][] events) {
        List<Event> eventList = new ArrayList<Event>();
        for (int[] event : events) {
            eventList.add(new Event(event[0], event[1], 0));
        }
        return eventList;
    }

    private int findTotalDays(List<Event> eventList) {
        int maxEndDay = -1;
        for (Event event : eventList) {
            if (event.endDay > maxEndDay) {
                maxEndDay = event.endDay;
            }
        }
        return maxEndDay;
    }

    public int maxEvents(int[][] events) {
        List<Event> eventList = prepareEventList(events);
        //[LEARNING] used for quicker finding of events starting on currDay
        eventList.sort((a, b) -> Integer.compare(a.startDay, b.startDay));

        //[LEARNING] min heap based on end Day event attend. So will pick event which ends sooner
        PriorityQueue<Integer> eventQueue = new PriorityQueue<Integer>();

        int totalDays = findTotalDays(eventList);

        int result = 0;
        int currDay = 1;
        int idx = 0;

        while (currDay <= totalDays) {
            //[LEARNING] adding to Queue for event's startDay is less than currDay and idx for resume from left off position
            while (
                idx < eventList.size() && eventList.get(idx).startDay <= currDay
            ) {
                eventQueue.add(eventList.get(idx).endDay);
                idx++;
            }

            while (!eventQueue.isEmpty() && eventQueue.peek() < currDay) {
                eventQueue.poll();
            }

            if (!eventQueue.isEmpty()) {
                //[LEARNING] Poll from queue to avoid attending same event again instead tracking attendedTime.
                eventQueue.poll();
                result += 1;
            }
            currDay += 1;
        }

        return result;
    }
}
