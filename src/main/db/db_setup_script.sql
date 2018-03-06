CREATE TABLE users(
  username VARCHAR(10),
  password VARCHAR(20),
  email VARCHAR(320),
  PRIMARY KEY (username)
);

CREATE TABLE day(
  four_five_am VARCHAR(255) NOT NULL DEFAULT '',
  five_six_am VARCHAR(255) NOT NULL DEFAULT '',
  six_seven_am VARCHAR(255) NOT NULL DEFAULT '',
  seven_eight_am VARCHAR(255) NOT NULL DEFAULT '',
  eight_nine_am VARCHAR(255) NOT NULL DEFAULT '',
  nine_ten_am VARCHAR(255) NOT NULL DEFAULT '',
  ten_eleven_am VARCHAR(255) NOT NULL DEFAULT '',
  eleven_twelve_pm VARCHAR(255) NOT NULL DEFAULT '',
  twelve_one_pm VARCHAR(255) NOT NULL DEFAULT '',
  one_two_pm VARCHAR(255) NOT NULL DEFAULT '',
  two_three_pm VARCHAR(255) NOT NULL DEFAULT '',
  three_four_pm VARCHAR(255) NOT NULL DEFAULT '',
  four_five_pm VARCHAR(255) NOT NULL DEFAULT '',
  five_six_pm VARCHAR(255) NOT NULL DEFAULT '',
  six_seven_pm VARCHAR(255) NOT NULL DEFAULT '',
  seven_eight_pm VARCHAR(255) NOT NULL DEFAULT '',
  eight_nine_pm VARCHAR(255) NOT NULL DEFAULT '',
  nine_ten_pm VARCHAR(255) NOT NULL DEFAULT '',
  ten_eleven_pm VARCHAR(255) NOT NULL DEFAULT '',
  eleven_twelve_am VARCHAR(255) NOT NULL DEFAULT '',
  twelve_one_am VARCHAR(255) NOT NULL DEFAULT '',
  one_two_am VARCHAR(255) NOT NULL DEFAULT '',
  two_three_am VARCHAR(255) NOT NULL DEFAULT '',
  three_four_am VARCHAR(255) NOT NULL DEFAULT '',
  username VARCHAR(20),
  PRIMARY KEY (username),
  CONSTRAINT username
    FOREIGN KEY (username)
    REFERENCES users (username)
    ON DELETE CASCADE
);

CREATE TABLE goal(
  title VARCHAR(255) NOT NULL DEFAULT '',
  description VARCHAR(1000) NOT NULL DEFAULT '',
  start_date DATE,
  target_date DATE,
  status VARCHAR(16) NOT NULL DEFAULT 'not_in_progress',
  archived BOOLEAN NOT NULL DEFAULT FALSE,
  username VARCHAR(20),
  PRIMARY KEY (username, title),
  CONSTRAINT username
  FOREIGN KEY (username)
  REFERENCES users (username)
  ON DELETE CASCADE
);
