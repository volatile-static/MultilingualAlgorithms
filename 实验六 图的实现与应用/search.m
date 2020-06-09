G = digraph([1 2 3 3 3 3 4 5 6 7 8 9 9 9 10], ...
    [7 6 1 5 6 8 2 4 4 3 7 1 6 8 2]);

t = dfsearch(G, 1, 'allevents', 'Restart', true);
visualize(G, t);

t = bfsearch(G, 1, 'allevents', 'Restart', true);
visualize(G, t);
