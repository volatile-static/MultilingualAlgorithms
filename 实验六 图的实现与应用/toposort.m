G = digraph([
    0 1 1 0 0 0 0
    0 0 0 1 0 0 0
    0 1 0 1 0 0 1
    0 0 0 0 1 1 0
    0 0 0 0 0 0 0
    0 0 0 0 1 0 0
    0 0 0 0 1 0 0]);
plot(G);
N = toposort(G)
