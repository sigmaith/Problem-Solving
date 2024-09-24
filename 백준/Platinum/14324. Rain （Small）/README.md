# [Platinum V] Rain (Small) - 14324 

[문제 링크](https://www.acmicpc.net/problem/14324) 

### 성능 요약

메모리: 17144 KB, 시간: 164 ms

### 분류

너비 우선 탐색, 그래프 이론, 그래프 탐색, 구현

### 제출 일자

2024년 9월 24일 22:01:25

### 문제 설명

<p>There's an island in the sea. The island can be described as a matrix with R rows and C columns, with H[i][j] indicating the height of each unit cell. Following is an example of a 3*3 island:</p>

<pre>3 5 5
5 4 5
5 5 5
</pre>

<p>Sometimes, a heavy rain falls evenly on every cell of this island. You can assume that an arbitrarily large amount of water falls. After such a heavy rain, some areas of the island (formed of one or more unit cells joined along edges) might collect water. This can only happen if, wherever a cell in that area shares an edge (not just a corner) with a cell outside of that area, the cell outside of that area has a larger height. (The surrounding sea counts as an infinite grid of cells with height 0.) Otherwise, water will always flow away into one or more of the neighboring areas (for our purposes, it doesn't matter which) and eventually out to sea. You may assume that the height of the sea never changes. We will use W[i][j] to denote the heights of the island's cells after a heavy rain. Here are the heights of the example island after a heavy rain. The cell with initial height 4 only borders cells with higher initial heights, so water will collect in it, raising its height to 5. After that, there are no more areas surrounded by higher cells, so no more water will collect. Again, note that water cannot flow directly between cells that intersect only at their corners; water must flow along shared edges.<br>
Following is the height of the example island after rain:</p>

<pre>3 5 5
5 5 5
5 5 5
</pre>

<p>Given the matrix of the island, can you calculate the total increased height sum(W[i][j]-H[i][j]) after a heavy rain?</p>

### 입력 

 <p>The first line of the input gives the number of test cases, T. T test cases follow.<br>
The first line of each test case contains two numbers R and C indicating the number of rows and columns of cells on the island. Then, there are R lines of C positive integers each. The j-th value on the i-th of these lines gives H[i][j]: the height of the cell in the i-th row and the j-th column.</p>

<h3>Limits</h3>

<ul>
	<li>1 ≤ T ≤ 100.</li>
	<li>1 ≤ H[i][j] ≤ 1000.</li>
	<li>1 ≤ R ≤ 10.</li>
	<li>1 ≤ C ≤ 10.</li>
</ul>

### 출력 

 <p>For each test case, output one line containing <code>Case #x: y</code>, where <code>x</code> is the test case number (starting from 1) and <code>y</code> is the total increased height.</p>

