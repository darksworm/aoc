#!/usr/bin/env bash

LEFT=()
RIGHT=()

while IFS=$'   ' read -r -a line
do
  LEFT+=("${line[0]}")
  RIGHT+=("${line[1]}")
done < input.txt

IFS=$'\n' SORTED_LEFT=($(sort <<<"${LEFT[*]}"))
IFS=$'\n' SORTED_RIGHT=($(sort <<<"${RIGHT[*]}"))

SUM=0

for i in "${!SORTED_LEFT[@]}"; do
  DIFF=$(expr ${SORTED_LEFT[$i]} - ${SORTED_RIGHT[$i]})
  ABS_DIFF=${DIFF#-}
  SUM=$(expr $SUM + $ABS_DIFF)
done

echo -n "Your result: $SUM"
