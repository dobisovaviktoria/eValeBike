document.addEventListener("DOMContentLoaded", () => {
    const addTechnicianBtn = document.getElementById("add-technician-btn");

    if (addTechnicianBtn) {
        addTechnicianBtn.addEventListener("click", () => {
            window.location.href = "/admin/technicians/add";
        });
    }

    const technicianTableBody = document.getElementById("technician-table-body");
    const paginationContainer = document.getElementById("pagination");

    const techniciansPerPage = 5;
    let currentPage = 1;
    let techniciansData = [];
    let allTechnicians = [];  // Store all technicians data for filtering

    const filterInput = document.querySelector("#filterValue"); // Updated ID
    const filterDropdown = document.querySelector("#filterType"); // Updated ID
    const notFoundMessage = document.getElementById("no-results-message");
    const searchBtn = document.querySelector("#search-btn");

    async function loadTechnicians() {
        try {
            const response = await fetch("/api/admin/technicians");
            if (!response.ok) throw new Error("Failed to fetch technicians");

            techniciansData = await response.json();
            allTechnicians = [...techniciansData];  // Store a copy for filtering
            renderTable();
            renderPagination();
        } catch (error) {
            console.error("Error loading technicians:", error);
        }
    }

    // Add event listener for the search button
    searchBtn.addEventListener("click", () => {
        console.log("Search button clicked"); // Debugging
        filterTechnicians(); // Call the filter function when search button is clicked
    });

    function filterTechnicians() {
        const filterText = filterInput.value.trim().toLowerCase();
        const filterOption = filterDropdown.value;

        console.log("Filtering by:", filterOption); // Debugging
        console.log("Search text:", filterText); // Debugging

        const filteredData = allTechnicians.filter(technician => {
            if (filterOption === "name") {
                return technician.name.toLowerCase().includes(filterText);
            } else if (filterOption === "email") {
                return technician.email.toLowerCase().includes(filterText);
            }
            return true; // Default case if no filter is selected
        });

        console.log("Filtered Data:", filteredData); // Debugging

        // Check if the element exists before modifying its style
        const notFoundMessage = document.getElementById("no-results-message");
        if (notFoundMessage) {
            if (filteredData.length === 0) {
                notFoundMessage.style.display = 'block';
            } else {
                notFoundMessage.style.display = 'none';
            }
        }

        // Update techniciansData and reset pagination
        techniciansData = filteredData;
        currentPage = 1; // Reset to the first page when filter is applied
        renderTable();
        renderPagination();
    }


    function renderTable() {
        technicianTableBody.innerHTML = "";
        const start = (currentPage - 1) * techniciansPerPage;
        const end = start + techniciansPerPage;
        const currentTechnicians = techniciansData.slice(start, end);

        currentTechnicians.forEach(technician => {
            const row = document.createElement("tr");
            row.dataset.id = technician.id;
            row.innerHTML = `
            <td>${technician.name}</td>
            <td>${technician.email}</td>
            <td>
                <button class="btn btn-outline-danger delete-btn" data-id="${technician.id}">
                    <i class="fa-solid fa-trash"></i>
                </button>
            </td>
        `;
            technicianTableBody.appendChild(row);
        });

        attachDeleteEventListeners();
    }

    function renderPagination() {
        paginationContainer.innerHTML = "";

        const totalPages = Math.ceil(techniciansData.length / techniciansPerPage);

        const createPageItem = (text, page, disabled = false, active = false) => {
            const li = document.createElement("li");
            li.className = `page-item ${disabled ? "disabled" : ""} ${active ? "active" : ""}`;
            const a = document.createElement("a");
            a.className = "page-link";
            a.href = "#";
            a.textContent = text;
            a.addEventListener("click", (e) => {
                e.preventDefault();
                if (!disabled) {
                    currentPage = page;
                    renderTable();
                    renderPagination();
                }
            });
            li.appendChild(a);
            return li;
        };

        paginationContainer.appendChild(
            createPageItem("Previous", currentPage - 1, currentPage === 1)
        );

        for (let i = 1; i <= totalPages; i++) {
            paginationContainer.appendChild(
                createPageItem(i, i, false, currentPage === i)
            );
        }

        paginationContainer.appendChild(
            createPageItem("Next", currentPage + 1, currentPage === totalPages)
        );
    }

    // Function to attach event listeners to delete buttons
    function attachDeleteEventListeners() {
        document.querySelectorAll(".delete-btn").forEach(button => {
            button.addEventListener("click", async (e) => {
                e.preventDefault();

                const technicianId = e.currentTarget.dataset.id; // Get technician ID from the button
                if (!technicianId) {
                    alert("Technician ID not found.");
                    return;
                }

                try {
                    // Send a DELETE request to the backend
                    const response = await fetch(`/api/admin/technicians/${technicianId}`, {
                        method: "DELETE",
                        headers: {
                            "Accept": "application/json",
                        },
                    });

                    if (response.ok) {
                        loadTechnicians(); // Refresh the table
                    } else {
                        alert("Failed to delete technician.");
                    }
                } catch (error) {
                    console.error("Error deleting technician:", error);
                    alert("An error occurred while deleting.");
                }
            });
        });
    }

    loadTechnicians();
});
