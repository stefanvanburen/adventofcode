"""
Solution to https://adventofcode.com/2018/day/1
"""
from collections import Counter
from pathlib import Path


# from the root of the project
INPUT_FILE = Path.cwd() / "2018" / "dec1.txt"


def part1() -> int:
    lines = [int(line) for line in INPUT_FILE.read_text().split("\n") if line]
    return sum(line for line in lines)


def part2() -> int:
    lines = [int(line) for line in INPUT_FILE.read_text().split("\n") if line]
    # add a 0 for the first frequency
    c = Counter([0])
    current_frequency = 0
    while True:
        # we keep iterating over the list, even when we hit the end
        for line in lines:
            current_frequency += line
            c.update([current_frequency])
            # break on the first frequency to get 2 counts
            if c[current_frequency] == 2:
                return current_frequency
