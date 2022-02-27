# Radix-Sort

Radix Sort is a non-comparative stable sorting algorithm that can be applied to both integers and strings. The values to be
sorted are usually represented as a “string of digits” or “string of characters”. The idea of Least Significant Digit (LSD)
Radix Sort is to perform multiple rounds of sorting, each working on a single digit from the least significant (rightmost)
digit to the most significant (leftmost) digit. In any round, the group of values to be sorted will be ordered based on the
particular digit we inspect in that round, this “semi-sorted” list, will then be used for the next round of sorting. The
number of rounds needed to sort a given group of values, therefore, is bounded by the maximum number of digits we need
to inspect, or the maximum length of the given values. We will always sort values in ascending order.

The particular Radix Sort we are implementing for this project uses a number of arrays to mimic the sorting in each round.
Each array corresponds to one possible value of a single digit. For a round r that inspects digit dr, all numbers to be sorted
that has the same value v at digit dr should be placed in one array, the one that corresponds to value v.

Example:

----------------------------
4 values: 230 639 827 107
Values removed: 0
Values padded: 0
4 values: 230 639 827 107
Press enter to continue ...
----------------------------
round 0:
0: [230]
7: [827 107]
9: [639]
Values after round:
4 values: 230 827 107 639
Press enter to continue ...
----------------------------
round 1:
0: [107]
2: [827]
3: [230 639]
Values after round:
4 values: 107 827 230 639
Press enter to continue ...
----------------------------
round 2:
1: [107]
2: [230]
6: [639]
8: [827]
Values after round:
4 values: 107 230 639 827
Press enter to continue ...
----------------------------
Sorted!
4 values: 107 230 639 827
----------------------------
