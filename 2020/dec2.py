"""
Solution to https://adventofcode.com/2020/day/2
"""
from collections import Counter
from pathlib import Path

# from the root of the project
INPUT_FILE = Path.cwd() / "2020" / "dec2.txt"


def part1() -> int:
    lines = [line for line in INPUT_FILE.read_text().split("\n") if line]
    valid_passwords = 0
    for line in lines:
        valid_count, letter, password = line.split(" ")
        letter = letter[0]
        low, high = valid_count.split("-")

        c = Counter(password)
        if c[letter] >= int(low) and c[letter] <= int(high):
            valid_passwords += 1

    return valid_passwords


def part2() -> int:
    lines = [line for line in INPUT_FILE.read_text().split("\n") if line]
    valid_passwords = 0
    for line in lines:
        positions, letter, password = line.split(" ")
        letter = letter[0]
        low, high = positions.split("-")

        c_low = password[int(low) - 1] == letter
        c_high = password[int(high) - 1] == letter

        if not (c_low and c_high) and (c_low or c_high):
            valid_passwords += 1

    return valid_passwords


print(part1())
print(part2())
