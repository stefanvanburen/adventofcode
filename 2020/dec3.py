"""
Solution to https://adventofcode.com/2020/day/2
"""
import math
from pathlib import Path

# from the root of the project
INPUT_FILE = Path.cwd() / "2020" / "dec3.txt"


def part1() -> int:
    lines = [line for line in INPUT_FILE.read_text().split("\n") if line]
    x_len = len(lines[0])
    cols = len(lines)
    x, y = 0, 0
    trees = 0
    while True:
        try:
            c = lines[y][x]
        except IndexError:
            break
        if c == "#":
            trees += 1
        y += 1
        x += 3
        x %= x_len

    return trees


def part2() -> int:
    lines = [line for line in INPUT_FILE.read_text().split("\n") if line]
    x_len = len(lines[0])
    cols = len(lines)
    total_trees = []
    for step_x, step_y in [[1, 1], [3, 1], [5, 1], [7, 1], [1, 2]]:
        x, y = 0, 0
        trees = 0
        while True:
            try:
                c = lines[y][x]
            except IndexError:
                break
            if c == "#":
                trees += 1
            y += step_y
            x += step_x
            x %= x_len

        total_trees.append(trees)

    return math.prod(total_trees)


print(part1())
print(part2())
