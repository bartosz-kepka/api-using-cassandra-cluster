import "./libs/shim/core.js";

export let options = { maxRedirects: 4 };

const Request = Symbol.for("request");
postman[Symbol.for("initial")]({
  options
});

export default function() {
  postman[Request]({
    name: "PUT",
    id: "12957d4d-20ba-4fea-8a66-a2ace6a1326a",
    method: "PUT",
    address:
      "http://127.0.0.1:8080/api/reviews/d797f860-5a95-11eb-8628-b9169fa055a3",
    data:
      '{\n    "country": "France",\n    "province": "Burgundy",\n    "region": "Pommard",\n    "vineyard": "Domaine du Pavillon Clos des Ursulines",\n    "winery": "Albert Bichot",\n    "variety": "Pinot Noir",\n    "price": 80.0,\n    "description": "With some firm structure, this wine also has great fruitiness. It offers plenty of raspberry fruits, good acidity and a solid texture of tannins. The acidity keeps the wine perfumed and fresh. Drink the wine from 2019.",\n    "points": 90\n}',
    headers: {
      Host: "test",
      "Content-Length": "476",
      Connection: "keep-alive",
      "Content-Type": "application/json"
    },
    post(response) {
      pm.test("Status code is 200", function() {
        pm.response.to.have.status(200);
      });
    }
  });
}
