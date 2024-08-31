package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

var dx = []int{0, 0, 1, -1}
var dy = []int{-1, 1, 0, 0}

func dfs(field [][]int, x, y, M, N int) {
	field[y][x] = 0
	for i := 0; i < 4; i++ {
		nx, ny := x+dx[i], y+dy[i]
		if nx >= 0 && ny >= 0 && nx < M && ny < N && field[ny][nx] == 1 {
			dfs(field, nx, ny, M, N)
		}
	}
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	tStr, _ := reader.ReadString('\n')
	t, _ := strconv.Atoi(strings.TrimSpace(tStr))

	for i := 0; i < t; i++ {
		// M, N, K 값 입력
		params, _ := reader.ReadString('\n')
		parts := strings.Fields(params)
		M, _ := strconv.Atoi(parts[0])
		N, _ := strconv.Atoi(parts[1])
		K, _ := strconv.Atoi(parts[2])

		// 배추밭을 0으로 초기화
		field := make([][]int, N)
		for i := range field {
			field[i] = make([]int, M)
		}

		for j := 0; j < K; j++ {
			coordStr, _ := reader.ReadString('\n')
			coords := strings.Fields(coordStr)
			x, _ := strconv.Atoi(coords[0])
			y, _ := strconv.Atoi(coords[1])
			field[y][x] = 1
		}

		// 필요한 지렁이 수 계산
		wormCount := 0
		for y := 0; y < N; y++ {
			for x := 0; x < M; x++ {
				if field[y][x] == 1 {
					dfs(field, x, y, M, N)
					wormCount++
				}
			}
		}

		fmt.Fprintln(writer, wormCount)
	}

}
