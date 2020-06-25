unit tree;

interface
type
  TTreeNode = record
    id: char;
    left, right: ^TTreeNode;
  end;

  TBinaryTree = class
    root: ^TTreeNode;
  public
    constructor Create();
    procedure First(n : TTreeNode);
    procedure Middle(n : TTreeNode);
    procedure Last(n : TTreeNode);
    procedure BFS;
    function Depth(n : TTreeNode) : Integer;
  end;

implementation
uses main, math, System.SysUtils;

procedure BuildPre(n : TTreeNode);
begin
  read(n.id);
  if n.id = #13 then  // 遇到回车结束
    Exit;
  new(n.left);
  BuildPre(n.left^);
  new(n.right);
  BuildPre(n.right^);
end;

constructor TBinaryTree.Create();
begin
  new(root);
  BuildPre(root^);
  {dispose(root);}
end;

procedure TBinaryTree.First(n : TTreeNode);
begin
  write(n.id);
  if n.left <> nil then
    First(n.left^);
  if n.right <> nil then
    First(n.right^);
end;

procedure TBinaryTree.Middle(n : TTreeNode);
begin
  if n.left <> nil then
    First(n.left^);
  write(n.id);
  if n.right <> nil then
    First(n.right^);
end;

procedure TBinaryTree.Last(n : TTreeNode);
begin
  if n.left <> nil then
    First(n.left^);
  if n.right <> nil then
    First(n.right^);
  write(n.id);
end;

procedure TBinaryTree.BFS;
var
  queue : OrderQueue;
  n : ^TTreeNode;
begin
  queue.push_back(root^);
  while not queue.empty() do
    begin
        n := queue.front();
        write(n.id);
        queue.pop_front();

        if n.left <> nil then
            queue.push_back(n.left);
        if n.right <> nil then
            queue.push_back(n.right);
    end
end;

function TBinaryTree.Depth(n : TTreeNode) : Integer;
begin
  if (n.left = nil) and (n.right = nil) then
    Exit(1);
  Result := 1 + max(Depth(n.left^), Depth(n.right^));
end;

end.
