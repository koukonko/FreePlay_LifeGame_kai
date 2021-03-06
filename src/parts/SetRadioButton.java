package parts;

import java.awt.Font;

import javax.swing.JRadioButton;

public class SetRadioButton extends JRadioButton{
	private int width;
	private int height;
	private int x;
	private int y;
	private boolean visible;

	// コンストラクタ
	public SetRadioButton(String text, int width, int height, int x, int y) {
		this(text, width, height, x, y, true);
	}

	// 表示・非表示 コンストラクタ
	public SetRadioButton(String text, int width, int height, int x, int y, boolean visible) {
		super(text);

		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.visible = visible;

		// ボタンの設定
		create();
	}

	private void create() {
		this.setBounds(this.x, this.y, this.width, this.height);
		this.setFont(new Font("メイリオ", Font.PLAIN, 12));
		this.setVisible(this.visible);
	}
}
