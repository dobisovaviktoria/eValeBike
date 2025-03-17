document.addEventListener("DOMContentLoaded", function () {
    const editButton = document.getElementById("editButton");
    const updateButton = document.getElementById("updateButton");
    const cancelButton = document.getElementById("cancelButton");
    const inputFields = document.querySelectorAll("input[readonly]");

    editButton.addEventListener("click", function () {
        inputFields.forEach(input => input.removeAttribute("readonly"));

        editButton.style.display = "none";
        updateButton.style.display = "inline-block";
        cancelButton.style.display = "inline-block";
    });

    cancelButton.addEventListener("click", function () {
        window.location.reload();
    });

    updateButton.addEventListener("click", async function () {
        const userId = document.getElementById("userId").value;
        const payload = {
            id: userId,
            name: document.getElementById("name").value,
            email: document.getElementById("email").value
        };

        console.log("Sending data:", payload);

        try {
            const response = await fetch(`/${userId}`, {
                method: "PATCH",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(payload)
            });

            const result = await response.json();
            console.log("Server response:", result);

            if (response.ok) {
                alert("Profile updated successfully!");
                window.location.reload();
            } else {
                alert("Error updating profile. Status: " + response.status);
            }
        } catch (error) {
            console.error("Update failed:", error);
            alert("Something went wrong.");
        }
    });
});
