unit main;

interface

uses
  System.SysUtils, System.Types, System.UITypes, System.Classes, System.Variants,
  FMX.Types, FMX.Controls, FMX.Forms, FMX.Graphics, FMX.Dialogs,
  FMX.Controls.Presentation, FMX.Edit, FMX.Objects, FMX.StdCtrls, tree;

type
  TForm1 = class(TForm)
    Circle1: TCircle;
    Line1: TLine;
    Edit1: TEdit;
    StyleBook1: TStyleBook;
    Line2: TLine;
    Circle2: TCircle;
    Line3: TLine;
    Edit2: TEdit;
    Line4: TLine;
    Circle3: TCircle;
    Line5: TLine;
    Edit3: TEdit;
    Line6: TLine;
    Circle4: TCircle;
    Edit4: TEdit;
    Circle5: TCircle;
    Edit5: TEdit;
    Edit6: TEdit;
    Circle6: TCircle;
    Edit7: TEdit;
    Circle7: TCircle;
    Line7: TLine;
    Circle8: TCircle;
    Edit8: TEdit;
    Line8: TLine;
    Circle9: TCircle;
    Line9: TLine;
    Edit9: TEdit;
    Line10: TLine;
    Line11: TLine;
    Edit10: TEdit;
    Line12: TLine;
    Circle10: TCircle;
    Edit11: TEdit;
    Circle11: TCircle;
    Edit12: TEdit;
    Edit13: TEdit;
    Circle12: TCircle;
    Edit14: TEdit;
    Circle13: TCircle;
    Circle14: TCircle;
    Circle15: TCircle;
    Edit15: TEdit;
    Line13: TLine;
    Line14: TLine;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Edit16: TEdit;
    Edit17: TEdit;
    Edit18: TEdit;
    Button1: TButton;
    Button2: TButton;
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
    tree: TBinaryTree;
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.fmx}

procedure TForm1.Button1Click(Sender: TObject);
begin
  tree := TBinaryTree.Create();
end;

end.
