CREATE TABLE users(
  username VARCHAR(10),
  password VARCHAR(20),
  PRIMARY KEY (username)
);

CREATE TABLE day(
  username VARCHAR(20),
  twelve_one VARCHAR(255) NOT NULL DEFAULT '',
  one_two VARCHAR(255) NOT NULL DEFAULT '',
  two_three VARCHAR(255) NOT NULL DEFAULT '',
  three_four VARCHAR(255) NOT NULL DEFAULT '',
  four_five VARCHAR(255) NOT NULL DEFAULT '',
  five_six VARCHAR(255) NOT NULL DEFAULT '',
  six_seven VARCHAR(255) NOT NULL DEFAULT '',
  seven_eight VARCHAR(255) NOT NULL DEFAULT '',
  eight_nine VARCHAR(255) NOT NULL DEFAULT '',
  nine_ten VARCHAR(255) NOT NULL DEFAULT '',
  ten_eleven VARCHAR(255) NOT NULL DEFAULT '',
  eleven_twelve VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (username),
  CONSTRAINT username
    FOREIGN KEY (username)
    REFERENCES users (username)
    ON DELETE CASCADE
);


