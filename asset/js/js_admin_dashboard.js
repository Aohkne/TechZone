const usersChart = document.getElementById("users-chart");
const earningsChart = document.getElementById("earnings-chart");
const combinedChart = document.getElementById("combined-chart");

new Chart(combinedChart, {
  type: "bar",
  data: {
    labels: [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ],
    datasets: [
      {
        label: "Users per month",
        backgroundColor: "#ffa726",
        data: [2, 5, 1, 20, 13, 2, 24, 12, 6, 12, 9, 14],
        borderWidth: 1,
      },
      {
        label: "Earnings per month",
        backgroundColor: "#66bb66",
        data: [14, 9, 11, 5, 8, 21, 2, 6, 18, 3, 4, 18],
        borderWidth: 1,
      },
    ],
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  },
});
new Chart(usersChart, {
  type: "bar",
  data: {
    labels: [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ],
    datasets: [
      {
        label: "Users per month",
        backgroundColor: ["#ffa726"],
        data: [2, 5, 1, 20, 13, 2, 24, 12, 6, 12, 9, 14],
        borderWidth: 1,
      },
    ],
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  },
});
new Chart(earningsChart, {
  type: "bar",
  data: {
    labels: [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ],
    datasets: [
      {
        label: "Earnings per month",
        backgroundColor: ["#66bb66"],
        data: [14, 9, 11, 5, 8, 21, 2, 6, 18, 3, 4, 18],
        borderWidth: 1,
      },
    ],
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  },
});
