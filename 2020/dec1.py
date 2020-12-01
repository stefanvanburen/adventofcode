"""
Solution to https://adventofcode.com/2020/day/1
"""
from pathlib import Path

# from the root of the project
INPUT_FILE = Path.cwd() / "2020" / "dec1.txt"


def part1() -> int:
    lines = [int(line) for line in INPUT_FILE.read_text().split("\n") if line]
    for line1 in lines:
        for line2 in lines:
            if line1 + line2 == 2020:
                return line1 * line2

    return 0


def part2() -> int:
    lines = [int(line) for line in INPUT_FILE.read_text().split("\n") if line]
    for line1 in lines:
        for line2 in lines:
            for line3 in lines:
                if line1 + line2 + line3 == 2020:
                    return line1 * line2 * line3

    return 0

print(part1())
print(part2())
