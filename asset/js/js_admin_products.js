const products = [
  {
    id: 1,
    image: "asset/img/img_all/img_product/img_phone/iphone13.jpg",
    name: "IPhone 13",
    price: 350,
    salePrice: 299,
    brand: "Apple",
    category: "Phone",
    madeIn: "United States",
    createDate: "20/9/2024",
    updateDate: "25/9/2024",
    stock: 5,
  },
  {
    id: 2,
    image: "asset/img/img_all/img_product/img_phone/oppoReno12.jpg",
    name: "IPhone 13",
    price: 350,
    salePrice: 299,
    brand: "Apple",
    category: "Phone",
    madeIn: "United States",
    createDate: "20/9/2024",
    updateDate: "25/9/2024",
    stock: 5,
  },
  {
    id: 3,
    image: "asset/img/img_all/img_product/img_phone/samsungA05.jpg",
    name: "IPhone 13",
    price: 350,
    salePrice: 299,
    brand: "Apple",
    category: "Phone",
    madeIn: "United States",
    createDate: "20/9/2024",
    updateDate: "25/9/2024",
    stock: 5,
  },
  {
    id: 4,
    image: "asset/img/img_all/img_product/img_phone/xiaomi13C.jpg",
    name: "IPhone 13",
    price: 350,
    salePrice: 299,
    brand: "Apple",
    category: "Phone",
    madeIn: "United States",
    createDate: "20/9/2024",
    updateDate: "25/9/2024",
    stock: 5,
  },
];
// RENDERING PRODUCTS
function renderTable() {
  // Create a document fragment to hold the table rows
  const fragment = document.createDocumentFragment();
  // Go through product list and append to fragment
  products.forEach((product) => {
    const tr = document.createElement("tr");

    tr.innerHTML = `
      <td>${product.id}</td>
      <td><img src="${product.image}" alt="${
      product.name
    }" class="product-img" /></td>
      <td>${product.name}</td>
      <td>$${product.price}</td>
      <td>${product.salePrice || "-"}</td>
      <td>${product.brand}</td>
      <td>${product.category}</td>
      <td>${product.madeIn}</td>
      <td>${product.createDate}</td>
      <td>${product.updateDate}</td>
      <td>${product.stock}</td>
      <td>
        <button class="edit-btn" onclick="showModal('edit-modal')" style="background: linear-gradient(60deg, #26c6da, #00acc1);">Edit</button>
        <button class="delete-btn" onclick="showModal('delete-modal')" style="background: linear-gradient(60deg, #ef5350, #e53935);">Delete</button>
      </td>
    `;

    fragment.appendChild(tr);
  });

  // Update the table body with fragment
  const tableBody = document.getElementById("table-body");
  tableBody.appendChild(fragment);
  // Update the card value
  document.querySelector(".card-value").innerHTML = products.length;
}
// Render table
renderTable();
