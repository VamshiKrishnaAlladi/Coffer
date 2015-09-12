package com.avk.coffer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class CofferPasswordDisplayFrame extends JDialog {

	private final JPanel popupFrame;
	private int xPressed, yPressed;

	private JPanel contentPanel;
	private static JPanel disablePanel;
	private static JLabel lblStatus;
	private static JButton focusGrab;
	private static CofferTextBlank titleBlank, usernameBlank, urlBlank;
	private static CofferPasswordBlank passwordBlank, confirmPasswordBlank;
	private static CofferToggleSwitch editSwitch;
	private static CofferButton submit;

	private String defaultStatus = "All the details of the entry are here Boss.";

	private int frameWidth = 500, frameHeight = 550;

	private boolean anyBlankEdited, titleEdited, urlEdited, usernameEdited, passwordEdited;

	public CofferPasswordDisplayFrame(CofferPasswordEntry p) {
		super(Coffer.frmcoffer, true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Coffer.setDisable(false);
			}
		});

		setUndecorated(true);
		setSize(frameWidth, frameHeight);

		JLayeredPane frmContent = new JLayeredPane();
		frmContent.setLocation(0, 0);
		frmContent.setSize(new Dimension(frameWidth, frameHeight));
		frmContent.setPreferredSize(new Dimension(frameWidth, frameHeight));

		disablePanel = new JPanel();
		disablePanel.setBackground(new Color(50, 50, 50, 100));
		disablePanel.setBounds(0, 0, frameWidth, frameHeight);
		disablePanel.setVisible(false);
		disablePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				disablePanel.grabFocus();
			}
		});
		frmContent.add(disablePanel);

		popupFrame = new JPanel();
		popupFrame.setBorder(new LineBorder(CofferReferences.CofferVeryLightGrey));
		popupFrame.setBackground(CofferReferences.CofferLightGrey);
		popupFrame.setSize(new Dimension(frameWidth, frameHeight));

		JLayeredPane titleBar = new JLayeredPane();
		titleBar.setPreferredSize(new Dimension(frameWidth - 2, 40));

		JLabel lblX = new JLabel("X");
		lblX.setForeground(Color.WHITE);
		lblX.setFont(CofferReferences.Antipasto_Bold_15);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setBounds(frameWidth - 35, 5, 30, 30);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coffer.setDisable(false);
				CofferPasswordDisplayFrame.this.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(CofferReferences.CofferBlue);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.WHITE);
			}
		});

		titleBar.add(lblX);

		JLabel titleLbl = new JLabel(p.getTitle() + " Entry");
		titleLbl.setOpaque(true);
		titleLbl.setBorder(new EmptyBorder(0, 25, 0, 0));
		titleLbl.setSize(new Dimension(frameWidth, 40));
		titleLbl.setBackground(CofferReferences.CofferDarkGrey);
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(CofferReferences.Comfortaa_Bold_Italic_16);
		titleLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xPressed = e.getX();
				yPressed = e.getY();
			}
		});
		titleLbl.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();

				CofferPasswordDisplayFrame.this.setLocation((x - xPressed), (y - yPressed));
			}
		});

		titleBar.add(titleLbl);

		focusGrab = new JButton("");
		focusGrab.setBounds(0, 0, 0, 0);
		focusGrab.grabFocus();
		popupFrame.setLayout(new BorderLayout(0, 0));
		titleBar.add(focusGrab);

		popupFrame.add(titleBar, BorderLayout.NORTH);

		contentPanel = new JPanel();
		contentPanel.setOpaque(false);
		contentPanel.setLayout(null);

		editSwitch = new CofferToggleSwitch("Edit");
		editSwitch.setBounds(frameWidth - 100, 30, 80, 20);
		editSwitch.addCofferToggleSwitchListener(new CofferToggleSwitchListener() {

			@Override
			public void onToggle() {
				if (editSwitch.isOn()) {
					titleBlank.setEditable(true);
					urlBlank.setEditable(true);
					usernameBlank.setEditable(true);
					passwordBlank.setEditable(true);
					submit.setVisible(true);
					anyBlankEdited = titleEdited = urlEdited = usernameEdited = passwordEdited = false;
				} else {
					if (anyBlankEdited) {
						String[] msgs = { "Your modifications will be discarded.", "Do you want to continue?" };
						setDisable(true);
						CofferDialog discardDialog = new CofferDialog(CofferPasswordDisplayFrame.this, true, "Discard Confirmation", msgs,
								CofferDialog.YES_NO_OPTIONS);
						setDisable(false);
						if (discardDialog.selectedOption == CofferDialog.YES_OPTION) {
							if (titleEdited)
								titleBlank.reset();
							if (urlEdited)
								urlBlank.reset();
							if (usernameEdited)
								usernameBlank.reset();
							if (passwordEdited) {
								passwordBlank.reset();
								confirmPasswordBlank.setVisible(false);
								confirmPasswordBlank.setEditable(true);
							}
						} else {
							editSwitch.toggle();
							return;
						}
					}
					titleBlank.setEditable(false);
					urlBlank.setEditable(false);
					usernameBlank.setEditable(false);
					passwordBlank.setEditable(false);
					submit.setVisible(false);
				}
			}

		});
		contentPanel.add(editSwitch);

		titleBlank = new CofferTextBlank("Title", p.getTitle());
		titleBlank.setBounds((frameWidth - 320) / 2, 80, 320, 50);
		titleBlank.setEditable(false);
		titleBlank.addCofferTextBlankEditListener(new CofferTextBlankEditListener() {

			@Override
			public void onValueChange() {
				anyBlankEdited = titleEdited = true;
			}

		});
		contentPanel.add(titleBlank);

		urlBlank = new CofferTextBlank("URL", p.getUrl());
		urlBlank.setBounds((frameWidth - 320) / 2, 140, 320, 50);
		urlBlank.setEditable(false);
		urlBlank.addCofferTextBlankEditListener(new CofferTextBlankEditListener() {

			@Override
			public void onValueChange() {
				anyBlankEdited = urlEdited = true;
			}

		});
		contentPanel.add(urlBlank);

		usernameBlank = new CofferTextBlank("Username", p.getUsername());
		usernameBlank.setBounds((frameWidth - 320) / 2, 200, 320, 50);
		usernameBlank.setEditable(false);
		usernameBlank.addCofferTextBlankEditListener(new CofferTextBlankEditListener() {

			@Override
			public void onValueChange() {
				anyBlankEdited = usernameEdited = true;
			}

		});
		contentPanel.add(usernameBlank);

		passwordBlank = new CofferPasswordBlank("Password", p.getPassword());
		passwordBlank.setBounds((frameWidth - 320) / 2, 260, 320, 50);
		passwordBlank.setEditable(false);
		passwordBlank.addCofferPasswordBlankEditListener(new CofferPasswordBlankEditListener() {

			@Override
			public void onValueChange() {
				anyBlankEdited = passwordEdited = true;
				confirmPasswordBlank.setVisible(true);
				confirmPasswordBlank.setEditable(true);
			}

		});
		contentPanel.add(passwordBlank);

		confirmPasswordBlank = new CofferPasswordBlank("Confirm Password", p.getPassword());
		confirmPasswordBlank.setBounds((frameWidth - 320) / 2, 320, 320, 50);
		confirmPasswordBlank.setEditable(false);
		confirmPasswordBlank.setVisible(false);
		contentPanel.add(confirmPasswordBlank);

		submit = new CofferButton("Submit");
		submit.setBounds((frameWidth - 200) / 2, 400, 200, 40);
		submit.setVisible(false);
		submit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					focusGrab.grabFocus();
					defaultStatus = getStatus();

					String title = titleBlank.getText().trim();
					String url = urlBlank.getText().trim();
					String username = usernameBlank.getText().trim();
					String password = passwordBlank.getText().trim();
					String conPass = confirmPasswordBlank.getText().trim();

					url = url.equals("") ? "no_url" : url;

					if (title.equals("")) {
						setStatus("I guess you don't know that Title is mandatory.");
						titleBlank.setValid(false);
						usernameBlank.setValid(true);
						passwordBlank.setValid(true);
						confirmPasswordBlank.setValid(true);
					} else if (username.equals("")) {
						setStatus("Comm'on you forgot entering your username?    O.o");
						titleBlank.setValid(true);
						usernameBlank.setValid(false);
						passwordBlank.setValid(true);
						confirmPasswordBlank.setValid(true);
					} else if (password.equals("")) {
						setStatus("Pick a strong password, empty ones don't serve the purpose.");
						titleBlank.setValid(true);
						usernameBlank.setValid(true);
						passwordBlank.setValid(false);
						confirmPasswordBlank.setValid(true);
					} else if (conPass.equals("")) {
						setStatus("All that's needed is to retype your password!");
						titleBlank.setValid(true);
						usernameBlank.setValid(true);
						passwordBlank.setValid(true);
						confirmPasswordBlank.setValid(false);
					} else if (!password.equals(conPass)) {
						setStatus("Try again! Your passwords didn't match.    :(");
						passwordBlank.setText("");
						passwordBlank.grabFocus();
						confirmPasswordBlank.setText("");
						titleBlank.setValid(true);
						usernameBlank.setValid(true);
						passwordBlank.setValid(false);
						confirmPasswordBlank.setValid(false);
					} else {
						titleBlank.setValid(true);
						usernameBlank.setValid(true);
						passwordBlank.setValid(true);
						confirmPasswordBlank.setValid(true);

						p.setTitle(title);
						p.setUrl(url);
						p.setUsername(username);
						p.setPassword(password);
						p.writeToFile();

						String[] msgs = { "Congratulations!", "Password entry edited successfully." };
						setDisable(true);
						new CofferDialog(CofferPasswordDisplayFrame.this, true, "Success Notification", msgs, CofferDialog.OK_OPTION);
						setDisable(false);
						CofferPasswordDisplayFrame.this.dispose();

					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		contentPanel.add(submit);

		popupFrame.add(contentPanel, BorderLayout.CENTER);

		frmContent.add(popupFrame);

		CofferPasswordDisplayFrame.this.setContentPane(frmContent);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		lblStatus = new JLabel(defaultStatus);
		lblStatus.setForeground(CofferReferences.CofferVeryLightGrey);
		lblStatus.setBorder(new EmptyBorder(0, 20, 0, 0));
		lblStatus.setPreferredSize(new Dimension(frameWidth, 20));
		lblStatus.setFont(CofferReferences.Comfortaa_Italic_13);

		popupFrame.add(lblStatus, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setModal(true);
		setResizable(false);
		setUndecorated(true);
		setVisible(true);
	}

	public static void setDisable(boolean flag) {
		disablePanel.setVisible(flag);
	}

	protected void setStatus(String string) {
		lblStatus.setText(string);
	}

	protected String getStatus() {
		return lblStatus.getText();
	}
}
