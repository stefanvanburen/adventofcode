"""
Solution to https://adventofcode.com/2018/day/2
"""
from collections import Counter
from pathlib import Path


# path from the root of the project
INPUT_FILE = Path.cwd() / "2018" / "dec2.txt"


def part1() -> int:
    lines = [line for line in INPUT_FILE.read_text().split("\n") if line]
    twos_count = 0
    threes_count = 0
    for line in lines:
        counter = Counter()
        counter.update(line)
        # to avoid counting a line twice
        have_two = False
        have_three = False
        for _, count in counter.items():
            if count == 2 and not have_two:
                twos_count += 1
                have_two = True
            elif count == 3 and not have_three:
                threes_count += 1
                have_three = True
    return twos_count * threes_count


def part2() -> str:
    lines = [line for line in INPUT_FILE.read_text().split("\n") if line]
    # this is inefficient, but oh well
    for line1 in lines:
        for line2 in lines:
            diff = _calculate_difference(line1, line2)
            if diff == 1:
                return "".join(c1 for c1, c2 in zip(line1, line2) if c1 == c2)


def _calculate_difference(str1: str, str2: str) -> int:
    """calculates the difference between two strings, character by character"""
    difference_count = 0
    for c1, c2 in zip(str1, str2):
        if c1 != c2:
            difference_count += 1
    return difference_count
