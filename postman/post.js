import "./libs/shim/core.js";

export let options = { maxRedirects: 4 };

const Request = Symbol.for("request");
postman[Symbol.for("initial")]({
  options
});

export default function() {
  postman[Request]({
    name: "POST",
    id: "a8b2d33e-3386-449c-ba62-93bda3d67520",
    method: "POST",
    address: "http://127.0.0.1:8080/api/reviews",
    data:
      '{\n    "country": "France",\n    "province": "Burgundy",\n    "region": "Pommard",\n    "vineyard": "Domaine du Pavillon Clos des Ursulines",\n    "winery": "Albert Bichot",\n    "variety": "Pinot Noir",\n    "price": 80.0,\n    "description": "With some firm structure, this wine also has great fruitiness. It offers plenty of raspberry fruits, good acidity and a solid texture of tannins. The acidity keeps the wine perfumed and fresh. Drink the wine from 2019.",\n    "points": 90\n}',
    headers: {
      Host: "test",
      "Content-Length": "476",
      Connection: "keep-alive",
      "Content-Type": "application/json"
    },
    post(response) {
      pm.test("Status code is 201", function() {
        pm.response.to.have.status(201);
      });
    }
  });
}
