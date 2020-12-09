import csv
import requests
import sys


def main():
    file = 'data.csv'
    url = 'http://{}:8080/api/reviews/'
    saved = 0
    to_save: int
    ip: string

    try:
        ip = sys.argv[1]
    except:
        print('Missing or invalid ip address. Localhost will be used.')
        ip = 'localhost'

    try:
        to_save = int(sys.argv[2])
    except:
        print('Missing or invalid argument. 10000 reviews will be saved')
        to_save = 10000

    url = url.format(ip)

    with open(file, newline='') as csv_file:
        csv_reader = csv.reader(csv_file)
        for row in csv_reader:
            row_no_blank_values = list(map(lambda value: value if value != '' else None, row))
            no, country, description, designation, points, \
            price, province, region_1, region_2, variety, winery = row_no_blank_values
            request_body = {
                'country': country,
                'description': description,
                'points': points,
                'price': price,
                'province': province,
                'region': region_1,
                'variety': variety,
                'vineyard': designation,
                'winery': winery
            }

            response = requests.post(url, json=request_body)
            if response.status_code == 201:
                saved += 1
                print('Saved {}/{}'.format(saved, to_save))
                if saved == to_save:
                    break
            else:
                print(response.text)

    print('Successfully saved {} reviews in the database'.format(saved))


if __name__ == '__main__':
    main()
