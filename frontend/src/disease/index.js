import { Table } from "reactstrap";
import useFetchState from "../util/useFetchState";
import tokenService from "../services/token.service";

const jwt = tokenService.getLocalAccessToken();

export default function DiseasesListing() {
    const [diseases, setDiseases] = useFetchState(
        [],
        /api/v1/diseases,
        jwt
        );

    const diseasesList  =
    diseases.map((disease) => {
            return (
                <tr key={disease.id}>
                    <td>{disease.name}</td>
                    <td>
                        <ul>
                            {Object.values(disease.susceptiblePetTypes).map((petType) => (
                                <li key={petType.id}>{petType.name}</li>
                            ))}
                        </ul>
                    </td>
                </tr>
            );
    });

    return (
            <div>
                <Table>
                    <thead>
                    <tr>
                        <th>name</th>
                        <th>Susceptible Pet Types</th>
                    </tr>
                    </thead>
                    <tbody>
                    {diseasesList}
                    </tbody>
                </Table>
            </div>

    );
}