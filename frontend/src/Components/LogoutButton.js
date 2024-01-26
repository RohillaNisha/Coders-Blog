const LogoutButton = () => {

    function deleteCookie(name) {
        document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
    }




    const handleLogout =  () => {

           deleteCookie('authToken');
            window.location.href = '/blogs';

    };

    return (
        <button onClick={handleLogout}>Logout</button>
    );
};

export default LogoutButton;