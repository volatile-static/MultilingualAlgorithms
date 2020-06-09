s = [1 1 1 2 5 3 6 4 7 8 8 8];
t = [2 3 4 5 3 6 4 7 2 6 7 5];
weights = [100 10 10 10 10 20 10 30 50 10 70 10];
G = graph(s,t,weights);
p = plot(G, 'EdgeLabel', G.Edges.Weight);
[T,pred] = minspantree(G);
highlight(p,T)
