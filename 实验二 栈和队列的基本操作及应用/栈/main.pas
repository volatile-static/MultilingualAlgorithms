unit main;

interface

uses
  System.SysUtils, System.Types, System.UITypes, System.Classes, System.Variants,
  FMX.Types, FMX.Controls, FMX.Forms, FMX.Graphics, FMX.Dialogs, FMX.StdCtrls,
  FMX.Controls.Presentation, FMX.Edit, FMX.EditBox, FMX.SpinBox, stacks;

type
  TForm1 = class(TForm)
    CornerButton1: TCornerButton;
    GroupBox1: TGroupBox;
    StyleBook1: TStyleBook;
    Button1: TButton;
    Button2: TButton;
    GroupBox2: TGroupBox;
    Label1: TLabel;
    GroupBox3: TGroupBox;
    Button3: TButton;
    Label2: TLabel;
    Button4: TButton;
    Edit1: TEdit;
    procedure CornerButton1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.fmx}

var
  st: TOrderStack;

procedure TForm1.Button1Click(Sender: TObject);
begin
  label1.Text := st.top
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
  st.pop
end;

procedure TForm1.Button3Click(Sender: TObject);
begin
  label2.Text := st.size.ToString;
end;

procedure TForm1.CornerButton1Click(Sender: TObject);
begin
  st.push(Edit1.Text[1])
end;

procedure TForm1.FormCreate(Sender: TObject);
begin
  st := TOrderStack.Create
end;

end.
