package January.jan30;

import java.util.*;
public class Coding {

    public static void main(String[] args) {
        RateLimiter_I rt = new RateLimiter_I();
        String u1 = UUID.randomUUID().toString();
        while (true) {
            System.out.println(rt.canConsumeResource(u1));
        }

    }

    /**
     * Concurrent Modification Exception on TreeSet.
     */
    static class RateLimiter_I {
        Map<String, RateLimitEntity> cache = new HashMap<>();

        public boolean canConsumeResource(String id) {
            long requestTime = System.currentTimeMillis();
            long minBack = getMinBack();
            if (!cache.containsKey(id)) {
                cache.put(id, new RateLimitEntity(id, 10, 10, 1));
            }

            RateLimitEntity rateLimitEntity = cache.get(id);
            if (rateLimitEntity.requestTimes.isEmpty()) {
                rateLimitEntity.requestTimes.add(requestTime);
                return true;
            } else {
                SortedSet sortedSet = rateLimitEntity.requestTimes.subSet(minBack, true, requestTime, true);
                if (sortedSet.size() + 1 > rateLimitEntity.hardLimit) {
                    return false;
                }
                SortedSet previous = rateLimitEntity.requestTimes.subSet(0L, true, minBack, true);
                rateLimitEntity.requestTimes.removeAll(previous);
                rateLimitEntity.requestTimes.add(requestTime);
                return true;
            }
        }

        private long getMinBack() {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, -1);
            Date min = cal.getTime();
            return min.getTime();
        }

        public RateLimitEntity getCurrentInformationOfUser(String id) {
            return cache.get(id);
        }

    }

    private static class RateLimitEntity {
        String id;
        TreeSet<Long> requestTimes = new TreeSet<>();
        int softLimit;
        int hardLimit;
        int windowSizeMin;

        public RateLimitEntity(String id, int softLimit, int hardLimit, int windSizeMin) {
            this.hardLimit = hardLimit;
            this.softLimit = softLimit;
            this.windowSizeMin = windSizeMin;
            this.id = id;
        }
    }

}
