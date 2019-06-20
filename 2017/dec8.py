#!/usr/bin/env python

PART1SOL = 5102
PART2SOL = 6056

registers = {}


def parse_line(line):
    tokens = line.split()
    reg = tokens[0]
    if reg not in registers:
        registers[reg] = 0
    op = tokens[1]
    val = int(tokens[2])
    cond = tokens[4:]
    if eval_cond(cond):
        if op == "inc":
            registers[reg] += val
        else:
            registers[reg] -= val


def eval_cond(cond):
    if cond[0] not in registers:
        registers[cond[0]] = 0
    # this is used as part of the eval
    v = registers[cond[0]]
    cond[0] = "v"
    return eval("".join(cond))


def part1():
    with open("./dec8.txt") as f:
        for line in f:
            parse_line(line)
        reg = max(registers, key=registers.get)
        return registers[reg]


def part2():
    with open("./dec8.txt") as f:
        m = 0
        for line in f:
            parse_line(line)
            reg = max(registers, key=registers.get)
            v = registers[reg]
            if v > m:
                m = v
        return m


if __name__ == "__main__":
    print(part1() == PART1SOL)
    print(part2() == PART2SOL)
