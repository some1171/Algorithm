package datastructure.graph.list;

/*
 * 
 * 1. Graph
 * - Adjacency Matrix
 * - Adjacency List
 * 
 * 2. Search
 * - Depth-First Search		(DFS) - Stack, Recursion
 * - Breadth-First Search	(BFS) - Queue
 *
 *    (0) 
 *   / 
 * (1) - (3)    (7)
 *  |  /  | \   /
 * (2) - (4) (5)
 *              \
 *              (6) - (8)
 *              
 * DFS:		0 - 1 - 3 - 5 - 7 - 6 - 8 - 4 - 2
 * BFS:		0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8
 * DFS R:	0 - 1 - 2 - 4 - 3 - 5 - 6 - 8 - 7
 * 
 * */

public class Test {
	public static void main(String[] args) {
		Graph graph = new Graph(9);
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(3, 5);
		graph.addEdge(5, 6);
		graph.addEdge(5, 7);
		graph.addEdge(6, 8);
		
		graph.dfs();
		//graph.bfs();
		//graph.dfsRecursive();
	}
}
