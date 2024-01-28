COPY public.quotes (quote, author, category)
FROM '/csv/quotes.csv'
DELIMITER ','
CSV HEADER;