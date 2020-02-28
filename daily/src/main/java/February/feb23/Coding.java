package February.feb23;

import java.util.*;
public class Coding {

    public class NextGreaterElement_III {
        public int nextGreaterElement(int n) {
            char[] ca = (n + "").toCharArray();
            
            int i;
            i = findFirstLowestNumberOnLeft(ca);

            if (i == 0) {
                return -1;
            }

            int smallest = getBiggerNumberOnRight(ca, i);

            swap(ca, i, smallest);

            Arrays.sort(ca, i, ca.length);

            long val = Long.parseLong(new String(ca));
            return (val <= Integer.MAX_VALUE) ? (int) val : -1;
        }

        private void swap(char[] number, int i, int smallest) {
            char temp = number[i - 1];
            number[i - 1] = number[smallest];
            number[smallest] = temp;
        }

        private int getBiggerNumberOnRight(char[] number, int i) {
            int j;
            int x = number[i - 1], smallest = i;
            for (j = i + 1; j < number.length; j++) {
                if (number[j] > x && number[j] <= number[smallest]) {
                    smallest = j;
                }
            }
            return smallest;
        }

        private int findFirstLowestNumberOnLeft(char[] number) {
            int i;
            for (i = number.length - 1; i > 0; i--) {
                if (number[i - 1] < number[i]) {
                    break;
                }
            }
            return i;
        }
    }

    class MincostToHireWorkers {
        public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
            Worker[] workers = new Worker[wage.length];
            for (int i = 0; i < wage.length; i++) {
                workers[i] = new Worker(quality[i], wage[i]);
            }
            Arrays.sort(workers, Comparator.comparingDouble(Worker::ratio));

            double result = Double.MAX_VALUE;
            int sum_of_quality = 0;
            PriorityQueue<Worker> pq = new PriorityQueue<>((a, b) -> b.quality - a.quality);
            for (Worker w : workers) {
                pq.add(w);
                sum_of_quality += w.quality;
                if (pq.size() > K) {
                    sum_of_quality -= pq.poll().quality;
                }
                if (pq.size() == K) {
                    result = Math.min(result, sum_of_quality * w.ratio);
                }
            }
            return result;
        }

        class Worker {
            int quality;
            int wage;
            double ratio;

            public Worker(int q, int w) {
                quality = q;
                wage = w;
                ratio = (double) wage / (double) quality;
            }

            public double ratio() {
                return ratio;
            }
        }
    }

    class Solution {
        int[][] directions = new int[][]{{2, 1}, {1, 2}, {2, -1}, {1, -2},
                {-2, 1}, {-1, 2}, {-2, -1}, {-1, -2}};

        public double knightProbability(int N, int K, int r, int c) {
            Queue<Element> q = new LinkedList();
            q.add(new Element(r, c, 0));
            int whatShouldHaveHappend = 0;
            int whatHaveCompletelyHappened = 0;
            while (!q.isEmpty()) {
                Element current = q.poll();
                if (current.count == K) {
                    whatShouldHaveHappend++;
                    whatHaveCompletelyHappened++;
                    continue;
                }
                boolean added = false;
                for (int[] dir : directions) {
                    int nx = current.x + dir[0];
                    int ny = current.y + dir[1];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                        q.add(new Element(nx, ny, current.count + 1));
                        added = true;
                    }
                }
                if (!added) {
                    whatHaveCompletelyHappened++;
                }
            }
            return whatShouldHaveHappend / whatHaveCompletelyHappened;
        }

        class Element {
            int x;
            int y;
            int count;

            public Element(int x, int y, int count) {
                this.x = x;
                this.y = y;
                this.count = count;
            }
        }
    }

}
