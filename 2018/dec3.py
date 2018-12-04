"""
Solution to https://adventofcode.com/2018/day/3
"""
from pathlib import Path
from collections import Counter


# path from the root of the project
INPUT_FILE = Path.cwd() / "2018" / "dec3.txt"


def part1() -> int:
    _, c = _shared()
    return c["X"]


def part2() -> int:
    parsed_lines, c = _shared()

    for i, (_, _, width, height) in enumerate(parsed_lines, start=1):
        expected = width * height
        if c[i] == expected:
            return i


def _shared():
    parsed_lines = [
        _parse_line(line)
        for line in INPUT_FILE.read_text().split("\n")
        if line
    ]

    max_ = 0
    # walk the lines once to find the maximum canvas size...
    for (left, top, width, height) in parsed_lines:
        max_ = max(max_, top+height, left+width)

    # create a blank 2-d canvas full of 0's
    canvas = [[0] * max_ for i in range(max_)]
    for i, (left, top, width, height) in enumerate(parsed_lines, start=1):
        for y in range(height):
            for x in range(width):
                if canvas[y+top][x+left] == 0:
                    canvas[y+top][x+left] = i
                else:
                    canvas[y+top][x+left] = "X"

    c = Counter()
    for line in canvas:
        c.update(line)

    return (parsed_lines, c)


def _parse_line(line: str):
    # we only care about the last two tokens
    tokens = line.split(" ")[2:]
    left, top = tokens[0].split(",")
    # strip the trailing colon
    top = top[:-1]
    width, height = tokens[1].split("x")
    return (int(left), int(top), int(width), int(height))
