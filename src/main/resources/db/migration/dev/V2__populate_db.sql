INSERT INTO users (name, password)
VALUES ('User1', '$2a$10$5NZBB.hGCwcabH5vuRJdw.UjAT/4Y88fV.x/zWnBrq2.a3UVcNMGq1'),
('User2', '$2a$10$PrADq.r9iRLrWIgA1iaUN.d2qkl0CW0FuNInDLgxIfCLw3WHeWTKu2'),
('User3', '$2a$10$8.D8UxRDxjePOmUdPKZG5uoadYhX4FP.m8uL4RmKCmKXVIsQ8AcF23');

INSERT INTO notes (id, name, content, access_type, user_id)
VALUES
  ('2f873118-58a1-4c56-aa5d-124ff5a0e6e8', 'Note 1', 'Content of Note 1', 'Private', 1),
  ('c4adfb6b-8859-4a33-87a9-8e5f2b1c3bdf', 'Note 2', 'Content of Note 2', 'Public', 1),
  ('f287c39b-bf04-47a4-98f1-322c17f84a38', 'Note 3', 'Content of Note 3', 'Private', 2),
  ('9bebb9cd-86a2-4a56-8ae6-251b7925354e', 'Note 4', 'Content of Note 4', 'Public', 2),
  ('d627e4d3-90af-4320-8c9e-9e208603f2a2', 'Note 5', 'Content of Note 5', 'Private', 2),
  ('e6b01755-0e20-44f9-9d47-912a06beeede', 'Note 6', 'Content of Note 6', 'Public', 3),
  ('5f3f532d-7224-4d7e-b439-b1e3b89e24f9', 'Note 7', 'Content of Note 7', 'Private', 3),
  ('a8b8eb23-4e6c-4b20-8d8a-b34c1963a48d', 'Note 8', 'Content of Note 8', 'Public', 3),
  ('6d23a628-88ae-4ef4-af75-1c22e7a13878', 'Note 9', 'Content of Note 9', 'Private', 3),
  ('f98105b0-5d9c-4cb6-9b57-0e6a3991e455', 'Note 10', 'Content of Note 10', 'Public', 3);