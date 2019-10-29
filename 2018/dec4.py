"""
Solution to https://adventofcode.com/2018/day/4
"""
from pathlib import Path

# path from the root of the project
INPUT_FILE = Path.cwd() / "2018" / "dec4.txt"


def part1() -> int:
    sorted_lines = sorted(line for line in INPUT_FILE.read_text().split("\n") if line)


if __name__ == "__main__":
    print(part1())
