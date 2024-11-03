// OBJECTS
const vouchers = [
  {
    id: 1,
    date: "25/9/2024",
    expireDate: "1/1/2025",
    salesPercentage: "10%",
    stock: 5,
  },
  {
    id: 2,
    date: "25/9/2024",
    expireDate: "1/1/2025",
    salesPercentage: "10%",
    stock: 5,
  },
  {
    id: 3,
    date: "25/9/2024",
    expireDate: "1/1/2025",
    salesPercentage: "10%",
    stock: 5,
  },
];
// DISPLAYING VOUCHERS
const voucherRows = vouchers
  .map((voucher) => {
    return `
    <tr>
        <td>${voucher.id}</td>
        <td>${voucher.date}</td>
        <td>${voucher.expireDate}</td>
        <td>${voucher.salesPercentage}</td>
        <td>${voucher.stock}</td>
        <td>
              <button
                style="background: linear-gradient(60deg, #ef5350, #e53935)"
                onclick="showModal('delete-modal')"
              >
                Delete
              </button>
            </td>
    </tr>
    `;
  })
  .join("");
// Update list
document.getElementById("table-body").innerHTML = voucherRows;
// Update Card
document.getElementsByClassName("card-value")[0].innerHTML = vouchers.length;
