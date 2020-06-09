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
  end;

implementation
uses main;

constructor TBinaryTree.Create();
begin

  new(root);
  dispose(root);
end;

end.
