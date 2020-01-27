package January.jan25;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
public class Coding {

    class MedianOfStreamOfNumbers_Hard {

        //MAX HEAP maintains LOWER elements and will be larger in size by min heap
        //lower end of the array in reverse order
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();

        /**
         * initialize your data structure here.
         */
        public MedianOfStreamOfNumbers_Hard() {

        }

        public void addNum(int num) {
            max.add(num);
            min.add(max.poll());
            if (max.size() < min.size()) {
                max.add(min.poll());
            }
            System.out.println("Adding... " + num);
            System.out.println("Max " + Arrays.toString(max.toArray()));
            System.out.println("Min " + Arrays.toString(min.toArray()));
        }

        public double findMedian() {
            return max.size() > min.size() ? max.peek() * 1.0 : (max.peek() + min.peek()) / 2.0;
        }
    }
}